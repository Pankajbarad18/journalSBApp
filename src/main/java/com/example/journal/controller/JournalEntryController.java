package com.example.journal.controller;

import com.example.journal.entity.JournalEntity;
import com.example.journal.entity.User;
import com.example.journal.repository.JournalEntryRepos;
import com.example.journal.service.JournalEntryService;
import com.example.journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("journal")
public class JournalEntryController {

//    private Map<Long,JournalEntity> journalEntries = new HashMap<>();
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @Autowired
    JournalEntryRepos journalEntryRepos;

    @GetMapping
    public ResponseEntity<?> getJournalEntries(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = Objects.requireNonNull(securityContext.getAuthentication()).getName();
        User user = userService.findByUserName(username).orElse(null);
//        return new ArrayList<>(journalEntries.values());
        System.out.println(user);
        if(user != null) {
            List<JournalEntity> all = user.getJournalEntityList();
            if (all != null && !all.isEmpty()) {
                return new ResponseEntity<List<JournalEntity>>(all, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



    @PostMapping()
    public JournalEntity addEntry(@RequestBody JournalEntity myEntity){
//        journalEntries.put(myEntity.getId(), myEntity);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = Objects.requireNonNull(securityContext.getAuthentication()).getName();
        myEntity.setDate(LocalDateTime.now());
        journalEntryService.addEntry(myEntity,username);
        return myEntity;
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntity> getById(@PathVariable ObjectId myId){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = Objects.requireNonNull(securityContext.getAuthentication()).getName();
        List<JournalEntity> userJournals = userService.findByusername(username).getJournalEntityList();
        Optional<JournalEntity> journal = userJournals.stream().filter(journalEntity -> journalEntity.getId().equals(myId)).findFirst();
        return journal.map(journalEntity -> new ResponseEntity<>(journalEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{myId}")
    public ResponseEntity<JournalEntity> updateById(@PathVariable ObjectId myId,@RequestBody JournalEntity journal){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = Objects.requireNonNull(securityContext.getAuthentication()).getName();
        List<JournalEntity> userJournals = userService.findByusername(username).getJournalEntityList().stream().filter(journalEntity -> journalEntity.getId().equals(myId)).toList();

        if(!userJournals.isEmpty()){
            JournalEntity old = userJournals.getFirst();
            if(old !=null && old.getId() != null){
                System.out.println("Updated journal entry: " + old.getTitle() + ", " + old.getContent());
                old.setTitle(!journal.getTitle().equals("") ? journal.getTitle() : old.getTitle());
                old.setContent(journal.getContent()!=null && !journal.getContent().equals("") ? journal.getContent() : old.getContent());
                journalEntryRepos.save(old);
                return new ResponseEntity<>(old,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{myId}")
    public ResponseEntity<?> removeById(@PathVariable ObjectId myId){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = Objects.requireNonNull(securityContext.getAuthentication()).getName();
        boolean removed = journalEntryService.deleteById(myId,username);
        return removed ? new ResponseEntity<>(HttpStatus.NO_CONTENT):new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
