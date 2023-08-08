package com.knkweb.spring5recipesapp.commands;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotesCommand {
     private Long id;
     private String recipeNotes;
}

