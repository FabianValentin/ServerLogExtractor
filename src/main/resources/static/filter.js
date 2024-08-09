// Function to filter servers based on region
function filterServers(filterRegionValue, filterStatusValue) {
    // Get all the div elements with class "products-row"
    const items = document.querySelectorAll('.products-row');

    items.forEach((item) => {

        // Get the span element inside the div
        const serverRegion = item.querySelector('.product-cell.category').childNodes[1].textContent.trim();
        const serverStatus = item.querySelector('.product-cell.status-cell').querySelector('span:nth-of-type(2)').textContent;

        console.log('filet region = ' + filterRegionValue + ' filter status = ' + filterStatusValue);
        if(filterStatusValue !== 'All Status' && filterRegionValue !== 'All Regions') {
            console.log('server region = ' + serverRegion + ' server status = ' + serverStatus);
            if (!serverRegion.startsWith(filterRegionValue) || serverStatus !== filterStatusValue) {
                item.style.display = 'none';
            } else {
                item.style.display = 'flex';
            }
        } else {
            if (filterStatusValue === 'All Status') {
                if (filterRegionValue === 'All Regions') {
                    // no filter applied
                    item.style.display = 'flex';
                } else {
                    if (!serverRegion.startsWith(filterRegionValue)) {
                        // Hide the server
                        item.style.display = 'none';
                    } else {
                        item.style.display = 'flex';
                    }
                }
            } else if (filterRegionValue === 'All Regions') {
                if(serverStatus !== filterStatusValue) {
                    item.style.display = 'none';
                } else {
                    item.style.display = 'flex';
                }
            }
        }
    });
}

function resetFilters() {
    // Get all the div elements with class "products-row"
    const items = document.querySelectorAll('.products-row');

    items.forEach((item) => {
        item.style.display = 'flex';
    });
}

document.getElementById('filter-button-apply').addEventListener('click', () => {
    const filterRegionValue = document.getElementById('region-option').value;
    const filterStatusValue = document.getElementById('status-option').value;
    filterServers(filterRegionValue, filterStatusValue);
});

document.getElementById('filter-button-reset').addEventListener('click', () => {
    resetFilters();
});