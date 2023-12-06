package ru.den.plannertodo.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// возможные значения, по которым можно искать категории
public class CategorySearchValue {
    private String title;
    private Long userId;
}
