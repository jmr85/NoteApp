package com.appjruiz.s05_responsive_design;

public interface NotesInteractionListener {
    void editNoteClick(Note note);
    void deleteNoteClick(Note note);
    void favoriteNoteClick(Note note);
}
