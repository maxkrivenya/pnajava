package com.example.apihell.service.utils;

import com.example.apihell.model.Mark;
import com.example.apihell.model.dto.MarkDTO;
import org.springframework.stereotype.Service;

@Service
public class MarkDTOMapper{
    public MarkDTO wrap(Mark mark){
        return new MarkDTO(
                mark.getValue(),
                mark.getSubject().getName(),
                mark.getDate()
        );
    }

    public Mark unwrap(MarkDTO markDTO){
        Mark mark = new Mark();
        mark.setDate(markDTO.date());
        mark.setValue(markDTO.value());
        return mark;
    }
}