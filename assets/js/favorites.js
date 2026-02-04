// Favorite events functionality
let filterActive = false;

function getFilterStateKey() {
    return 'devnexus-filter-state-' + window.location.pathname;
}

function toggleFavorite(eventId) {
    const favorites = getFavorites();
    const icon = document.getElementById('fav-' + eventId);
    
    if (favorites.includes(eventId)) {
        // Remove from favorites
        const index = favorites.indexOf(eventId);
        favorites.splice(index, 1);
        icon.setAttribute('fill', 'none');
        icon.classList.remove('active');
    } else {
        // Add to favorites
        favorites.push(eventId);
        icon.setAttribute('fill', 'currentColor');
        icon.classList.add('active');
    }
    
    localStorage.setItem('devnexus-favorites', JSON.stringify(favorites));
    
    // Update visibility if filter is active
    if (filterActive) {
        applyFavoritesFilter();
    }
}

function getFavorites() {
    const stored = localStorage.getItem('devnexus-favorites');
    return stored ? JSON.parse(stored) : [];
}

function initializeFavorites() {
    const favorites = getFavorites();
    favorites.forEach(eventId => {
        const icon = document.getElementById('fav-' + eventId);
        if (icon) {
            icon.setAttribute('fill', 'currentColor');
            icon.classList.add('active');
        }
    });
    
    // Restore filter state for this page
    const savedFilterState = localStorage.getItem(getFilterStateKey());
    if (savedFilterState === 'true') {
        filterActive = true;
        const filterText = document.getElementById('filter-text');
        const filterBtn = document.getElementById('favorites-filter-btn');
        
        if (filterText && filterBtn) {
            filterText.textContent = 'My Sessions';
            filterBtn.classList.add('active');
            applyFavoritesFilter();
        }
    }
}

function toggleFavoritesFilter() {
    filterActive = !filterActive;
    const filterText = document.getElementById('filter-text');
    const filterBtn = document.getElementById('favorites-filter-btn');
    
    // Save filter state for this page
    localStorage.setItem(getFilterStateKey(), filterActive.toString());
    
    if (filterActive) {
        filterText.textContent = 'My Sessions';
        filterBtn.classList.add('active');
        applyFavoritesFilter();
    } else {
        filterText.textContent = 'All Events';
        filterBtn.classList.remove('active');
        showAllEvents();
    }
}

function applyFavoritesFilter() {
    const favorites = getFavorites();
    const allEventCards = document.querySelectorAll('.event-card');
    const allTimeSlots = document.querySelectorAll('.timeline-slot');
    
    allEventCards.forEach(card => {
        const eventId = card.getAttribute('data-event-id');
        const sessionCard = card.closest('.session-card');
        
        if (favorites.includes(eventId)) {
            sessionCard.style.display = '';
        } else {
            sessionCard.style.display = 'none';
        }
    });
    
    // Hide time slots that have no visible events
    allTimeSlots.forEach(slot => {
        const visibleCards = slot.querySelectorAll('.session-card:not([style*="display: none"])');
        if (visibleCards.length === 0) {
            slot.style.display = 'none';
        } else {
            slot.style.display = '';
        }
    });
}

function showAllEvents() {
    const allSessionCards = document.querySelectorAll('.session-card');
    const allTimeSlots = document.querySelectorAll('.timeline-slot');
    
    allSessionCards.forEach(card => {
        card.style.display = '';
    });
    
    allTimeSlots.forEach(slot => {
        slot.style.display = '';
    });
}

// Initialize favorites on page load
document.addEventListener('DOMContentLoaded', initializeFavorites);
