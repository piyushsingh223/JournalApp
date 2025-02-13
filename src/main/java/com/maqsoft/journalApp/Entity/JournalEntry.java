package com.maqsoft.journalApp.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;


@Document(collection = "journal_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JournalEntry {

    @Id
    //@Indexed(unique = true)
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
}
