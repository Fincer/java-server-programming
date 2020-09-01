// Pekka Helenius <fincer89@hotmail.com>, Fjordtek 2020

package com.fjordtek.chapter1_httprequest.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.fjordtek.chapter1_httprequest.web.PageMapper;

@Controller
@ResponseBody
public class HttpRequestHandler {

    protected static final String indexPageURL   = "index";
    protected static final String contactPageURL = "contact";
    protected static final String helloPageURL   = "hello";

    private PageMapper           pageMapper           = new PageMapper();
    private HttpServerLogger     httpServerLogger     = new HttpServerLogger();
    private HttpExceptionHandler httpExceptionHandler = new HttpExceptionHandler();

    private ResponseEntity<String> generateDefaultWebsiteResponse(
            String webPageURL,
            String HumanReadableName,
            HttpServletRequest clientRequestData
    ) {
        String siteName = pageMapper.mapWebPageNames(webPageURL, HumanReadableName);
        httpServerLogger.logMessageNormal(
                clientRequestData,
                "HTTPOK"
        );
        return new ResponseEntity<>(
                "This is the " + siteName + " page\n",
                HttpStatus.OK
        );
    }

    // Assignment 1 - controller and endpoints
    @RequestMapping(
            value = indexPageURL,
            method = RequestMethod.GET
    )
    public ResponseEntity<String> generateIndexPage(HttpServletRequest requestData) {
        return generateDefaultWebsiteResponse(
                indexPageURL,
                "main",
                requestData
        );
    }

    @RequestMapping(
            value = contactPageURL,
            method = RequestMethod.GET
    )
    public ResponseEntity<String> generateContactPage(HttpServletRequest requestData) {

        return generateDefaultWebsiteResponse(
                contactPageURL,
                "contact",
                requestData
        );
    }

    // Redirect hard-set root URL / to index subpage
    // (~default landing page on many web server configurations (index.php, index.html, ...) )
    @RequestMapping(
            value = "/",
            method =  RequestMethod.GET
    )
    @ResponseStatus(HttpStatus.FOUND)
    public void redirectToIndex(HttpServletResponse serverResponse) {
        serverResponse.setHeader("Location", "/" + indexPageURL);
        serverResponse.setStatus(302);
    }
    /*
    Alternatively:

    ResponseEntity<Void> redirectToIndex() {
        return ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create("/" + indexPageURL))
            .build();
    }
     */

    // Assignment 2 - Request parameters

    /*
     * Multiple parameters given (see that 'method' can have multiple values assigned
     * Just a brief example/demonstration
     * NOTE: MIME type 'x-www-form-urlencoded' works with curl, at least. Change it if needed.
     * curl 'http://localhost:8080/index' --data 'location=moon&name=John'
     *
     */
    @RequestMapping(
            value    = { helloPageURL },
            method   = { RequestMethod.POST },
            produces = { "application/html" },
            consumes = "application/x-www-form-urlencoded",
            params   = { "location", "name" }
    )
    public ResponseEntity<String> getNameAndLocation(
            @RequestParam(
                    value        = "location",
                    required     = true,
                    defaultValue = ""
            ) String location,
            @RequestParam(
                    value        = "name",
                    required     = true,
                    defaultValue = ""
            ) String name,
            HttpServletRequest requestData
    )
    {

        if (location == null ||
                name == null ||
                location.equals("") ||
                name.equals("") ||
                requestData.getParameterMap().size() > 2
        ) {
            return httpExceptionHandler.notFoundErrorHandler(requestData);
        }

        httpServerLogger.logMessageNormal(
                requestData,
                "HTTPOK"
        );

        return new ResponseEntity<>(
                "Welcome to the " + location + " " + name + "!\n",
                HttpStatus.OK
        );
    }
}
