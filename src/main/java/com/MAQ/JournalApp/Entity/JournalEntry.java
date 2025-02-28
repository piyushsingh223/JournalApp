package com.MAQ.JournalApp.Entity;

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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public @NonNull String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
