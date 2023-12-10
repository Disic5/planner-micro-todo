package ru.den.plannerusers.serch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSearchValues {

    private String email;

    private String name;

    //постраничность
    private Integer pageNumber;
    private Integer pageSize;

    //сортировка
    private String sortColumn;
    private String sortDirection;
}
