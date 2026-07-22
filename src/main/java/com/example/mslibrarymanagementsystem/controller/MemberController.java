package com.example.mslibrarymanagementsystem.controller;

import com.example.mslibrarymanagementsystem.criteria.PageCriteria;
import com.example.mslibrarymanagementsystem.dto.request.MemberRequest;
import com.example.mslibrarymanagementsystem.dto.response.BookResponse;
import com.example.mslibrarymanagementsystem.dto.response.MemberResponse;
import com.example.mslibrarymanagementsystem.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createMember(@Valid @RequestBody MemberRequest memberRequest) {
        memberService.createMember(memberRequest);
    }

    @GetMapping
    public Page<MemberResponse> getAllMembers(PageCriteria pageCriteria) {
        return memberService.getMembers(pageCriteria);
    }

    @GetMapping("/{id}")
    public MemberResponse getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping("/{id}/books")
    public List<BookResponse> getBorrowedBooksByMemberId(@PathVariable Long id) {
        return memberService.getBorrowedBooksByMemberId(id);
    }

    @PutMapping("/{id}")
    public MemberResponse updateMember(@PathVariable Long id,
                             @Valid @RequestBody MemberRequest request) {
        return memberService.updateMember(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}