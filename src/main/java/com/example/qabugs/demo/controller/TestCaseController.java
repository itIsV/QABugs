package com.example.qabugs.demo.controller;


import com.example.qabugs.demo.model.TestCase;
import com.example.qabugs.demo.model.dto.TestCaseDto;
import com.example.qabugs.demo.service.TestCaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class TestCaseController {

    TestCaseService testCaseService;

    @GetMapping("/api/testcases/new/{something}")
        public ResponseEntity<String> createNothing(@PathVariable String something, UriComponentsBuilder uriComponentsBuilder) {

            String response = testCaseService.createNothing(something);
            URI uri = uriComponentsBuilder.path("/api/testcases/{variable}")
                    .buildAndExpand(something)
                    .toUri();

            return ResponseEntity.created(uri).body(response);

    }

    @PostMapping("/api/testcases/new")
        public ResponseEntity<TestCaseDto> createTest(@RequestBody TestCase testCase, UriComponentsBuilder uriComponentsBuilder) {

            TestCase newTestCase = testCaseService.createTest(testCase);
            URI uri = uriComponentsBuilder.path("/api/testcases/{id}")
                .buildAndExpand(newTestCase.getId_case())
                .toUri();

            TestCaseDto newTestCaseDto = TestCaseDto
                    .builder()
                    .idCase(newTestCase.getId_case())
                    .description(newTestCase.getDescription())
                    .tested(newTestCase.getTested())
                    .passed(newTestCase.getPassed())
                    .number_of_tries(newTestCase.getNumber_of_tries())
                    .last_update(newTestCase.getLast_update()
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .build();

            return ResponseEntity.created(uri).body(newTestCaseDto);

    }

    @GetMapping("/api/testcases/{id}")
        public ResponseEntity<TestCaseDto> getTestByID(@PathVariable Long id) {

            TestCase testCase = testCaseService.getTestByID(id);

            TestCaseDto testCaseDto = TestCaseDto
                    .builder()
                    .idCase(testCase.getId_case())
                    .description(testCase.getDescription())
                    .tested(testCase.getTested())
                    .passed(testCase.getPassed())
                    .number_of_tries(testCase.getNumber_of_tries())
                    .last_update(testCase.getLast_update()
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .build();

            return ResponseEntity.ok(testCaseDto);

    }

    @GetMapping("/api/testcases")
        public ResponseEntity<List<TestCaseDto>> getTests() {

            List<TestCase> testCases = testCaseService.getTests();

            List<TestCaseDto> testCasesDto = new ArrayList();

            testCases.stream().forEach(testCase -> {
                testCasesDto.add(TestCaseDto
                        .builder()
                        .idCase(testCase.getId_case())
                        .description(testCase.getDescription())
                        .tested(testCase.getTested())
                        .passed(testCase.getPassed())
                        .number_of_tries(testCase.getNumber_of_tries())
                        .last_update(testCase.getLast_update()
                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .build());
            });

            return ResponseEntity.ok(testCasesDto);

    }

    @PutMapping("/api/testcases/{id}")
        public ResponseEntity<TestCaseDto> updateTestCaseByID(@PathVariable Long id, @RequestBody TestCase testCase) {

            TestCase oldTestCase = testCaseService.getTestByID(id);

            testCase.setId_case(oldTestCase.getId_case());
            testCaseService.createTest(testCase);

            TestCaseDto testCaseDto = TestCaseDto
                    .builder()
                    .idCase(oldTestCase.getId_case())
                    .description(testCase.getDescription())
                    .tested(testCase.getTested())
                    .passed(testCase.getPassed())
                    .number_of_tries(testCase.getNumber_of_tries())
                    .last_update(testCase.getLast_update()
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .build();


            return ResponseEntity.ok(testCaseDto);

    }

    @DeleteMapping("/api/testcases/{id}")
        public ResponseEntity<Void> deleteTestByID(@PathVariable Long id) {

        testCaseService.deleteTestByID(id);

        return ResponseEntity.noContent().build();

    }

}
