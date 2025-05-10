package com.abhyam.quiz_service.model;

import lombok.Data;

import java.util.List;

@Data
public class QuizDTO {

    private String title;
    private List<Long> questionIds;
}
