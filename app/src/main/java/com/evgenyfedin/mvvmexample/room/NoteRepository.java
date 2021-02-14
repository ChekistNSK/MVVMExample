package com.evgenyfedin.mvvmexample.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        insertNoteRx(note);
//        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
//        new UpdateNoteAsyncTask(noteDao).execute(note);
        updateNoteRx(note);
    }

    public void delete(Note note) {
//        new DeleteNoteAsyncTask(noteDao).execute(note);
        deleteNoteRx(note);
    }

    public void deleteAllNotes() {
//        new DeleteAllNotesAsyncTask(noteDao).execute();
        deleteAllNotesRx();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private void insertNoteRx(Note note) {
        Completable.fromAction(() -> {
            noteDao.insert(note);
        })
                .onErrorComplete()
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private void updateNoteRx(Note note) {
        Completable.fromAction(() -> {
            noteDao.update(note);
        })
                .onErrorComplete()
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private void deleteNoteRx(Note note) {
        Completable.fromAction(() -> {
            noteDao.delete(note);
        })
                .onErrorComplete()
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private void deleteAllNotesRx() {
        Completable.fromAction(() -> {
            noteDao.deleteAllNotes();
        })
                .onErrorComplete()
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

//    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
//        private NoteDao noteDao;
//
//        private InsertNoteAsyncTask(NoteDao noteDao) {
//            this.noteDao = noteDao;
//        }
//
//        @Override
//        protected Void doInBackground(Note... notes) {
//            noteDao.insert(notes[0]);
//            return null;
//        }
//    }

//    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
//        private NoteDao noteDao;
//
//        private UpdateNoteAsyncTask(NoteDao noteDao) {
//            this.noteDao = noteDao;
//        }
//
//        @Override
//        protected Void doInBackground(Note... notes) {
//            noteDao.update(notes[0]);
//            return null;
//        }
//    }

//    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
//        private NoteDao noteDao;
//
//        private DeleteNoteAsyncTask(NoteDao noteDao) {
//            this.noteDao = noteDao;
//        }
//
//        @Override
//        protected Void doInBackground(Note... notes) {
//            noteDao.delete(notes[0]);
//            return null;
//        }
//    }

//    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
//        private NoteDao noteDao;
//
//        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
//            this.noteDao = noteDao;
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            noteDao.deleteAllNotes();
//            return null;
//        }
//    }

}
