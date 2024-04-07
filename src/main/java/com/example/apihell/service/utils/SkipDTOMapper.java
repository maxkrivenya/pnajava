package com.example.apihell.service.utils;

import com.example.apihell.model.Skip;
import com.example.apihell.model.dto.SkipDTO;
import org.springframework.stereotype.Service;

@Service
public class SkipDTOMapper {
    public SkipDTO wrap(Skip skip) {
        return new SkipDTO(
                skip.getValue(),
                skip.getIsJustified(),
                skip.getSubject().getName(),
                skip.getDate()
        );
    }

    public Skip unwrap(SkipDTO skipDTO) {
        Skip skip = new Skip();
        skip.setValue(skipDTO.value());
        skip.setIsJustified(skipDTO.isJustified());
        skip.setDate(skipDTO.date());
        return skip;
    }
}