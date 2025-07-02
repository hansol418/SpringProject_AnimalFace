package com.project.animalface_web.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
public class ImageClassifyController {

    @PostMapping("/classify")
    public ResponseEntity<Map<String, Object>> classifyImage(@RequestParam("image") MultipartFile image) {
        log.info("Request received at /classify endpoint.");

        if (image.isEmpty()) {
            log.error("No file was submitted.");
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "No file was submitted.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        log.info("Image received: " + image.getOriginalFilename());

        String apiUrl = "http://localhost:8000/classify/";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + image.getOriginalFilename());
            image.transferTo(convFile);

            log.info("Converted file: " + convFile.getAbsolutePath());

            HttpPost uploadFile = new HttpPost(apiUrl);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("image", convFile);
            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);

            log.info("Sending POST request to: " + apiUrl);

            HttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();
            String apiResult = EntityUtils.toString(responseEntity, "UTF-8");

            log.info("API Response: " + apiResult);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(apiResult);


            String predictedLabel = rootNode.path("predictedClassLabel").asText();
            double confidence = rootNode.path("confidence").asDouble(); // 정확도 추가

            log.info("predictedLabel: " + predictedLabel);
            log.info("confidence: " + confidence);


            Map<String, Object> jsonResponse = new HashMap<>();
            jsonResponse.put("predictedClassLabel", predictedLabel); // 일관성 있게 필드 이름 수정
            jsonResponse.put("confidence", confidence); // 정확도 추가
            jsonResponse.put("status", "success");


            if (!convFile.delete()) {
                System.err.println("Failed to delete the temporary file.");
            }

            return ResponseEntity.ok(jsonResponse);

        } catch (IOException e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "File processing error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
