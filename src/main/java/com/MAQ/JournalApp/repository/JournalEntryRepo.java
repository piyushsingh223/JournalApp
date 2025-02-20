package com.MAQ.JournalApp.repository;

//import com.maqsoft.journalApp.Entity.JournalEntry;
import com.MAQ.JournalApp.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {

}
