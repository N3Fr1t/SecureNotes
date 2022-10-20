package com.securenotes.controller;

import com.securenotes.model.Note;
import com.securenotes.repository.NoteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class NoteController {
    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping
    public String check(Map<String, Object> model) {
        Iterable<Note> notes = noteRepository.findAll();

        model.put("notes", notes);

        return "notes";
    }

    @PostMapping
    public String add(@RequestParam String title, @RequestParam String text, Map<String, Object> model) {
        Note note = new Note(title, text);

        noteRepository.save(note);

        Iterable<Note> notes = noteRepository.findAll();

        model.put("notes", notes);

        return "notes";
    }
}
