package com.example.mz.sigunUp.service;

import com.example.mz.global.exception.CustomException;
import com.example.mz.global.exception.ExceptionCode;
import com.example.mz.sigunUp.dto.SignUpResponseDto;
import com.example.mz.sigunUp.entity.Path;
import com.example.mz.sigunUp.repo.SignUpRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUpService {
    private final SignUpRepo signUpRepo;
    @Value("${careerNet.apiKey}")
    private String careerApiKey;

    public SignUpResponseDto.PathRet readPath(String path) {
        Path p = signUpRepo.findById(path).orElseThrow(() -> new CustomException(ExceptionCode.PATH_NOT_FOUNT));
        return SignUpResponseDto.PathRet.builder()
                .pathCategory(p.getPathCategory())
                .build();
    }
    public String getSchool(SignUpResponseDto.SchoolReq req) throws IOException, URISyntaxException {
        StringBuilder result = new StringBuilder();

        String urlStr = "https://www.career.go.kr/cnet/openapi/getOpenApi.json?apiKey="+careerApiKey+"&svcType=api&svcCode=SCHOOL&gubun=univ_list&searchUnivNm="+req.getSchool();
        URL url = new URI(urlStr).toURL();
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));

        String line;
        while((line = br.readLine()) != null){
            log.info(line);
            result.append(line).append("\n");
        }
        br.close();
        urlConnection.disconnect();

        return result.toString();
    }
    public List<SignUpResponseDto.SchoolRet> getStringSchool(String school) throws Exception {
        StringBuilder result = new StringBuilder();
        String encodedSchool = java.net.URLEncoder.encode(school, StandardCharsets.UTF_8);

        String urlStr = "https://www.career.go.kr/cnet/openapi/getOpenApi.json?apiKey="+careerApiKey+"&svcType=api&svcCode=SCHOOL&gubun=univ_list&perPage=30&searchSchulNm="+encodedSchool;
        URL url = new URI(urlStr).toURL();
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        String line;
        while((line = br.readLine()) != null){
            result.append(line).append("\n");
        }
        br.close();
        urlConnection.disconnect();
        String re = result.toString();
        List<SignUpResponseDto.SchoolRet> parsedSchool = extractSchoolNameFromXML(re);
        return parsedSchool;
    }

//    받아온 학교 정보에서 이름만 parsing
public List<SignUpResponseDto.SchoolRet> extractSchoolNameFromXML(String xmlData) throws Exception {
    // XML 데이터를 파싱하기 위한 DocumentBuilder 인스턴스 생성
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();

    // String 형태의 XML 데이터를 Document 객체로 변환
    Document doc = builder.parse(new InputSource(new StringReader(xmlData)));

    // <schoolName>,<adres> 태그를 가진 모든 요소를 찾음
    NodeList schoolNameList = doc.getElementsByTagName("schoolName");
    NodeList regionList = doc.getElementsByTagName("adres");

    List<SignUpResponseDto.SchoolRet> schools = new ArrayList<>();

    for (int i = 0; i < schoolNameList.getLength(); i++) {
        Element schoolNameElement = (Element) schoolNameList.item(i);
        Element regionElement = (Element) regionList.item(i);

        SignUpResponseDto.SchoolRet school = SignUpResponseDto.SchoolRet.builder()
                .school(schoolNameElement.getTextContent())
                .region(regionElement.getTextContent())
                .build();

        schools.add(school);
    }

    if (schools.isEmpty()) {
        throw new CustomException(ExceptionCode.SCHOOL_NOT_FOUND);
    }

    return schools;
}

    public void createPath(SignUpResponseDto.PathReq req) {
        Path p = Path.builder()
                .path(req.getPath())
                .pathCategory(req.getPathCategory())
                .build();
        signUpRepo.save(p);
    }
}
