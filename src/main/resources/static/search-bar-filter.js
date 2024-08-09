const searchBar = document.getElementById('searchBar');
const items = document.querySelectorAll('.products-row');

searchBar.addEventListener('input', () => {
    const searchTerm = searchBar.value.toLowerCase();

    items.forEach((item) => {
        const itemText = item.textContent.toLowerCase();
        if (itemText.includes(searchTerm)) {
            item.style.display = 'flex';
        } else {
            item.style.display = 'none';
        }
    });
});