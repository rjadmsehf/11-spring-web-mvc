package com.ohgiraffers.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileuploadController {

    @Autowired
    private ResourceLoader resourceLoader;

    @PostMapping("single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile,
                                   @RequestParam String singleFileDescription,
                                   Model model) throws IOException {

        System.out.println("singleFile = " + singleFile);
        System.out.println("singleFileDescription = " + singleFileDescription);

        /* 파일을 저장할 경로 설정  */
        Resource resource = resourceLoader.getResource("classpath:static/img/single");  //필기 build 쪽 경로이다 .

        String filePath = null;

        if(!resource.exists()) {
            /* 필기 만약 static 폴더에 파일이 없는 경우 만들어준다. */
            String root = "src/main/resources/static/img/single";
            File file = new File(root);

            file.mkdirs();     // 필기 메이크 디렉토리 파일을 만들어줌

            filePath = file.getAbsolutePath();
        } else {
            filePath = resourceLoader.getResource("classpath:static/img/single").getFile().getAbsolutePath();
        }

        /* 필기. 파일명 변경 처리  */
        String originFileName = singleFile.getOriginalFilename();  //필기 << 파일의 오리지널 이름을 가져오는 친구
        System.out.println("originFileName = " + originFileName);

        /* 필기 확장자 제거 */
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        System.out.println("ext = " + ext);

        String savedName = UUID.randomUUID().toString().replace("-","") + ext;  //필기 replace 지울 문자열
        System.out.println("savedName = " + savedName); //필기 랜덤으로 만들 이름 인데  위에 문장으로 - 문자열을 지웠음

        /* 필기 파일을 저장 */

        singleFile.transferTo(new File(filePath + "/" + savedName)); // 필기 랜덤으로 생성한 이름에 "/" 경로를 붙여준다
        model.addAttribute("message", "파일 업로드 성공!!!!!");    //필기 result 에 표시해줄 문자열
        model.addAttribute("img", "static/img/single/" + savedName);
        return "result";

    }

    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multiFiles
            , @RequestParam String multiFileDescription
            , Model model) throws IOException {

        System.out.println("multipartFiles = " + multiFiles);
        System.out.println("multiFileDescription = " + multiFileDescription);

        Resource resource = resourceLoader.getResource("classpath:static/img/multi");
        String filePath = null;

        if(!resource.exists()) {

            String root = "src/main/resources/static/img/multi";
            File file = new File(root);
            file.mkdirs();

            filePath = file.getAbsolutePath();
        } else {
            filePath = resourceLoader.getResource("classpath:static/img/multi").getFile().getAbsolutePath();
        }

        System.out.println("filePath = " + filePath);

        List<FileDTO> files = new ArrayList<>();
        List<String> saveFiles = new ArrayList<>();

        for(MultipartFile file : multiFiles) {
            /* 파일명 변경 처리 */
            String originFileName = file.getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

            files.add(new FileDTO(originFileName, savedName, filePath, multiFileDescription));

            file.transferTo(new File(filePath + "/" + savedName));
            saveFiles.add("static/img/multi/" + savedName);
        }

        model.addAttribute("message", "파일 업로드 성공!!!!!!!!");
        model.addAttribute("imgs" , saveFiles);

        return "result";
    }
}