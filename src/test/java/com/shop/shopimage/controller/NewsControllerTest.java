//package com.shop.shopimage.controller;
//
//
////import jdk.internal.jshell.tool.ConsoleIOContext;
//import com.shop.shopimage.model.rabbitMQ.Sender;
//import com.shop.shopimage.model.rabbitMQ.Writer;
//import lombok.NoArgsConstructor;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.*;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//
//class NewsControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    Sender sender = new Sender();
//    Writer writer = new Writer();
//
//    @Test
//    public void givenSaveBasicInfo_whenCorrectInput_thenSuccess() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/addNews")
//                        .accept(MediaType.TEXT_HTML)
//                        .param("name", "")
//                        .param("password", "pass"))
//                .andExpect(view().name("success"))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    public  void listN(){
//ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
//ListNode list2= new ListNode(1, new ListNode(3, new ListNode(4)));
//
//        mergeTwoLists( list1,  list2);
//
//    }
//
//
//    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
//
//        if(list1!=null && list2!=null){
//            if(list1.val<list2.val){
//                list1.next=mergeTwoLists(list1.next,list2);
//                return list1;
//            }
//            else{
//                list2.next=mergeTwoLists(list1,list2.next);
//                return list2;
//            }
//        }
//        if(list1==null)
//            return list2;
//        return list1;
//    }
//
//@NoArgsConstructor
// class     ListNode {
//    int val;
//    ListNode next;
//
//      ListNode(int val) { this.val = val; }
//     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//  }
//
//}