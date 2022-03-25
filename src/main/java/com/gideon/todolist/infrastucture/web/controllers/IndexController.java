package com.gideon.todolist.infrastucture.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class IndexController {

    @GetMapping(value = "", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String welcomePage() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\" />\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "        <title> TODO Backend Application </title>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <main>\n" +
                "            <code>\n" +
                "                \n" +
                "                    {\n" +
                "                        <pre>   \"message\": \"Service Available.\"</pre>\n" +
                "                    }\n" +
                "                </pre>\n" +
                "            </code>\n" +
                "        </main>\n" +
                "    </body>\n" +
                "</html>";
    }
}
