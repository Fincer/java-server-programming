//Pekka Helenius <fincer89@hotmail.com>, Fjordtek 2020

package com.fjordtek.chapter2_task3_friendlist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.fjordtek.chapter2_task3_friendlist.domain.Friend;

@Controller
public class FriendController {

    protected static final String landingPageURL      = "index";

    private HttpServerLogger     httpServerLogger     = new HttpServerLogger();
    private HttpExceptionHandler httpExceptionHandler = new HttpExceptionHandler();

    private List<Friend>         friendList           = new ArrayList<>();

    // NOTE NOTE
    // We could alternatively use two attributes (firstName & lastName) for names.
    // However, only single query parameter 'friend' must be given, according to the assignment description.
    // Therefore, parse/split it internally.
    // Additionally: Internal, native constraint checks should be used/preferred here.
    private String splitFriendName(@Valid Friend friend, HttpServletRequest requestData) {

        String[] fullNameParts = friend.toString().split(" ");

        if (fullNameParts.length != 2) {
        	httpServerLogger.commonError("Invalid name submitted", requestData, friend.toString());
            return "Invalid name";
        } else {

            for (int i = 0; i < fullNameParts.length; i++) {

                if (!fullNameParts[i].matches("^[a-zA-Z]+$")) {
                    httpServerLogger.commonError("Invalid name submitted", requestData, "Error in string", fullNameParts[i]);
                    return "Invalid name";
                }
                fullNameParts[i] = fullNameParts[i].substring(0,1).toUpperCase() + fullNameParts[i].substring(1);
            }

            String name = fullNameParts[0].trim() + ' ' + fullNameParts[1].trim();
            friend.setFullName(name);

            // Ref: https://stackoverflow.com/questions/18852059/java-list-containsobject-with-field-value-equal-to-x
            // Ref: https://knpcode.com/java/variable-scope-java-lambda-expression/
            // Ref: https://www.geeksforgeeks.org/stream-anymatch-java-examples/
            if (!this.friendList.stream().anyMatch(obj -> friend.getFullName().equals(obj.getFullName()))) {
                this.friendList.add(friend);
                return "Name OK";
            } else {
                return "Name already in the list";
            }
        }

    }

    @RequestMapping(
        value    = landingPageURL,
        method   = RequestMethod.GET
    )
    public String FriendWebFormGet(
        @RequestParam(
            value        = "friend",
            required     = false
        ) @Valid Friend friend,
        Model dataModel,
        HttpServletRequest requestData
        )
    {

        if (friend != null) {
            dataModel.addAttribute("formvalidation", this.splitFriendName(friend, requestData));
        }

        dataModel.addAttribute("friend", friend);
        dataModel.addAttribute("friends", this.friendList);

        httpServerLogger.logMessageNormal(
            requestData,
            "HTTPOK"
        );

        return landingPageURL;

    }

    // Redirect
    @RequestMapping(
        value  = "/",
        method =  RequestMethod.GET
    )
    @ResponseStatus(HttpStatus.FOUND)
    public String redirectToFriendWebForm() {
        return "redirect:" + landingPageURL;
    }

    // Other URL requests
    @RequestMapping(
        value  = "*",
        method = { RequestMethod.GET, RequestMethod.POST }
    )
    public String ErrorWebForm(HttpServletRequest requestData) {
        return httpExceptionHandler.notFoundErrorHandler(requestData);
    }

    // NOTE: No POST method implementation here since assignment description required *GET method use only*
}
