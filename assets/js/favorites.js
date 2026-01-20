// Favorite events functionality
function toggleFavorite(eventId) {
    const favorites = getFavorites();
    const icon = document.getElementById('fav-' + eventId);
    
    if (favorites.includes(eventId)) {
        // Remove from favorites
        const index = favorites.indexOf(eventId);
        favorites.splice(index, 1);
        icon.setAttribute('fill', 'none');
        icon.classList.remove('text-red-500');
        icon.classList.add('text-gray-400');
    } else {
        // Add to favorites
        favorites.push(eventId);
        icon.setAttribute('fill', 'currentColor');
        icon.classList.remove('text-gray-400');
        icon.classList.add('text-red-500');
    }
    
    localStorage.setItem('devnexus-favorites', JSON.stringify(favorites));
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
            icon.classList.remove('text-gray-400');
            icon.classList.add('text-red-500');
        }
    });
}

// Initialize favorites on page load
document.addEventListener('DOMContentLoaded', initializeFavorites);
