package me.phx.web.controler;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.*;
import java.nio.file.*;

@RestController
public class UploadController {
    @RequestMapping(value="/upload", method= RequestMethod.GET)
    public String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                ReadableByteChannel byteChannel = Channels.newChannel(file.getInputStream());

                Path path = Paths.get("/tmp", name);
                FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
                fileChannel.transferFrom(byteChannel, 0, file.getSize());

//                System.out.println(Files.readAllLines(path));
                System.out.println(new String(file.getBytes()));
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
}
