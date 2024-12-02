window.onload = function() {
    var buttons = document.querySelectorAll('button[data-status]');
    buttons.forEach(function(button) {
        var status = parseInt(button.getAttribute('data-status'));
        if (status === 1) {
            button.style.backgroundColor = '#6c757d'; 
            button.textContent = 'Đã thanh toán';
        } else {
            button.style.backgroundColor = '#28a745';  
            button.textContent = 'Thanh toán';
        }
    });
}
    
    