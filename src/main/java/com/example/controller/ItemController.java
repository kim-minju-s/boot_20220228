package com.example.controller;

import java.io.IOException;
import java.util.List;

import com.example.entity.Item;
import com.example.service.ItemDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = {"/item"})
public class ItemController {

    @Autowired
    private ItemDB itemDB;

    @GetMapping(value = "/insert")
    public String insertGET(){
        // item 폴더의 insert.jsp를 표시
        return "item/insert";
    }

    // 물품 추가하기
    @PostMapping(value = "/insert")
    public String insertPOST(
            @ModelAttribute Item item,
            @RequestParam(name = "image") MultipartFile file ) throws IOException {

        System.out.println(item.toString());
        System.out.println(file.getOriginalFilename());

        if (file.isEmpty() == false) {   // 첨부됨
            item.setFilename(file.getOriginalFilename());
            item.setFilesize(file.getSize());
            item.setFiletype(file.getContentType());
            // getBytes는 오류처리 해야함(throws IOException)
            item.setFiledata(file.getBytes());
        }
        System.out.println("파일데이터" + item.toString());
        int ret = itemDB.insertItem(item);
        if (ret == 1) {
            // 주소가 바뀌는지만 확인
            return "redirect:/item/selectlist";
        }

        // GET으로 변경 127.0.0.1:8080/item/insert
        return "redirect:/item/insert";
    }

    // 목록 조회
    // 127.0.0.1:8080/item/selectlist?page=1
    // @RequestMapping(value="/selectlist",method=RequestMethod.GET)
    @GetMapping(value = "selectlist")
    public String selectListGET(
            Model model, 
            @RequestParam(name = "page", defaultValue = "0") int page){

        if (page == 0) {    // ?page=1을 추가하는 부분
            return "redirect:/item/selectlist?page=1";
        }

        // 1 페이지 => 0
        // 2 페이지 => 1
        Pageable pageable = PageRequest.of( page-1, 10);

        // DB에서 목록 가져오기(가져온 정보들을 하나로 합치기)
        List<Item> list = itemDB.selectListItem(pageable);

        // jsp로 전달
        model.addAttribute("list", list);
        model.addAttribute("pages", 10);

        // item 폴더의 select.jsp 를 표시
        return "item/select";
    }

    // 이미지 1개 조회
    // 127.0.0.1:8080/item/image?code=2
    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> imageGET(
            @RequestParam(name = "code") long code){

        try {
            Item item = itemDB.selectOneItemImage(code);
            if (item.getFilesize() > 0) {   // 이미지가 있음
                HttpHeaders headers = new HttpHeaders();

                if (item.getFiletype().equals("image/jpeg")) {
                    headers.setContentType(MediaType.IMAGE_JPEG);
                }
                else if (item.getFiletype().equals("image/png")) {
                    headers.setContentType(MediaType.IMAGE_PNG);
                }
                else if (item.getFiletype().equals("image/gif")) {
                    headers.setContentType(MediaType.IMAGE_GIF);
                }

                ResponseEntity<byte[]> response
                    = new ResponseEntity<byte[]>(item.getFiledata(), headers, HttpStatus.OK);

                return response;
            }
            else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 물품 상세조회
    @GetMapping(value = "/selectone")
    public String selectOneGET(Model model,
        @RequestParam(name = "code") long code ){

        Item item = itemDB.selectOneItem(code);

        model.addAttribute(item);

        return "redirect:/item/selectone";
    }

}
