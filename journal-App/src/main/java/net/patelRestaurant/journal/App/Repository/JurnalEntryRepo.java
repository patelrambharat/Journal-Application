package net.patelRestaurant.journal.App.Repository;

import net.patelRestaurant.journal.App.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JurnalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {

}
