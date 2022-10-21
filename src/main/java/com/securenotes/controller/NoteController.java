package com.securenotes.controller;

import com.securenotes.model.Note;
import com.securenotes.repository.NoteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping
    public String view(@RequestParam(required = false, defaultValue = "") String search, Model model) {
        Iterable<Note> notes;

        if (search != null && !search.isEmpty()) {
            notes = noteRepository.findByTitle(search);
        } else {
            notes = noteRepository.findAll();
        }

        model.addAttribute("notes", notes);
        model.addAttribute("search", search);

        return "notes";
    }

    @PostMapping
    public String add(@RequestParam String title, @RequestParam String text, Model model) {
        Note note = new Note(title, text);

        noteRepository.save(note);

        Iterable<Note> notes = noteRepository.findAll();

        model.addAttribute("notes", notes);

        return "notes";
    }
}
