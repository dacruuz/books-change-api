package br.com.bookschange.api.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CurrentCondition {
    NEW,
    EXCELLENT,
    GOOD,
    REGULAR,
    BAD
}
