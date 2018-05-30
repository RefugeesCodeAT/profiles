package at.refugeescode.profiles.controller;

import at.refugeescode.profiles.logic.ProfileService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoadingImage {
    private ProfileService profileService;

    public LoadingImage(ProfileService profileService) {
        this.profileService = profileService;
    }
    @RequestMapping(value = "/imageController/{imageId}")
    @ResponseBody
    @GetMapping
    public void loadingImage(@PathVariable long imageId, HttpServletResponse response,
                           HttpServletRequest request) throws IOException {
        byte[] picture = profileService.findOne(imageId).get().getPicture();
        response.setContentType("image/jpeg");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(picture);
        outputStream.close();
    }
}
