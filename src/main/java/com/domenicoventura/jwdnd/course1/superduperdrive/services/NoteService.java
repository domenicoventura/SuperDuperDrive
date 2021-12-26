package com.domenicoventura.jwdnd.course1.superduperdrive.services;

import com.domenicoventura.jwdnd.course1.superduperdrive.mapper.NoteMapper;
import com.domenicoventura.jwdnd.course1.superduperdrive.mapper.UserMapper;
import com.domenicoventura.jwdnd.course1.superduperdrive.model.File;
import com.domenicoventura.jwdnd.course1.superduperdrive.model.Note;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class NoteService {
    private final UserMapper userMapper;
    private final NoteMapper noteMapper;

    public NoteService(UserMapper userMapper, NoteMapper noteMapper) {
        this.userMapper = userMapper;
        this.noteMapper = noteMapper;
    }

    public void addNote(String title, String description, String userName) {
        Integer userId = userMapper.getUser(userName).getUserId();
        Note note = new Note(0, title, description, userId);
        noteMapper.insert(note);
    }

    public Note[] getNoteListings(Integer userId) {
        return noteMapper.getNotesForUser(userId);
    }

    public Note getNote(Integer noteId) {
        return noteMapper.getNote(noteId);
    }

    public void deleteNote(Integer noteId) {
        noteMapper.deleteNote(noteId);
    }

    public void updateNote(Integer noteId, String title, String description) {
        noteMapper.updateNote(noteId, title, description);
    }
}