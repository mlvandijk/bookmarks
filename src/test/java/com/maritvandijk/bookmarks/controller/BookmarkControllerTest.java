package com.maritvandijk.bookmarks.controller;

import com.maritvandijk.bookmarks.model.Bookmark;
import com.maritvandijk.bookmarks.repository.BookmarkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookmarkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @BeforeEach
    void setUp() {
        bookmarkRepository.deleteAll();
    }

    @Test
    void listBookmarks_ShouldReturnBookmarksPage() throws Exception {
        // Given
        Bookmark bookmark = new Bookmark();
        bookmark.setTitle("Test Bookmark");
        bookmark.setUrl("http://example.com");
        bookmarkRepository.save(bookmark);

        // When/Then
        mockMvc.perform(get("/bookmarks"))
                .andExpect(status().isOk())
                .andExpect(view().name("bookmarks/index"))
                .andExpect(model().attributeExists("bookmarks"));
    }

    @Test
    void newBookmarkForm_ShouldReturnFormPage() throws Exception {
        mockMvc.perform(get("/bookmarks/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("bookmarks/form"))
                .andExpect(model().attributeExists("bookmark"));
    }

    @Test
    void editBookmarkForm_WithValidId_ShouldReturnFormPage() throws Exception {
        // Given
        Bookmark bookmark = new Bookmark();
        bookmark.setTitle("Test Bookmark");
        bookmark.setUrl("http://example.com");
        Bookmark saved = bookmarkRepository.save(bookmark);

        // When/Then
        mockMvc.perform(get("/bookmarks/{id}/edit", saved.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("bookmarks/form"))
                .andExpect(model().attributeExists("bookmark"));
    }

    @Test
    void editBookmarkForm_WithInvalidId_ShouldReturnErrorView() throws Exception {
        mockMvc.perform(get("/bookmarks/{id}/edit", 999L))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attribute("error", "Invalid bookmark Id:999"));
    }

    @Test
    void viewBookmark_WithInvalidId_ShouldReturnErrorView() throws Exception {
        mockMvc.perform(get("/bookmarks/{id}", 999L))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attribute("error", "Invalid bookmark Id:999"));
    }

    @Test
    void viewBookmark_WithValidId_ShouldReturnViewPage() throws Exception {
        // Given
        Bookmark bookmark = new Bookmark();
        bookmark.setTitle("Test Bookmark");
        bookmark.setUrl("http://example.com");
        Bookmark saved = bookmarkRepository.save(bookmark);

        // When/Then
        mockMvc.perform(get("/bookmarks/{id}", saved.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("bookmarks/view"))
                .andExpect(model().attributeExists("bookmark"));
    }

    @Test
    void createBookmark_ShouldSaveAndRedirect() throws Exception {
        mockMvc.perform(post("/bookmarks")
                        .param("title", "New Bookmark")
                        .param("url", "http://example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bookmarks"));
    }

    @Test
    void updateBookmark_ShouldUpdateAndRedirect() throws Exception {
        // Given
        Bookmark bookmark = new Bookmark();
        bookmark.setTitle("Original Title");
        bookmark.setUrl("http://example.com");
        Bookmark saved = bookmarkRepository.save(bookmark);

        // When/Then
        mockMvc.perform(post("/bookmarks/{id}", saved.getId())
                        .param("title", "Updated Title")
                        .param("url", "http://updated-example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bookmarks"));
    }

    @Test
    void deleteBookmark_ShouldDeleteAndRedirect() throws Exception {
        // Given
        Bookmark bookmark = new Bookmark();
        bookmark.setTitle("To Delete");
        bookmark.setUrl("http://example.com");
        Bookmark saved = bookmarkRepository.save(bookmark);

        // When/Then
        mockMvc.perform(delete("/bookmarks/{id}", saved.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bookmarks"));
    }
}
