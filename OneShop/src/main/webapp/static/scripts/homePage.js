document.addEventListener('DOMContentLoaded', () => {
    const slides = document.querySelectorAll('.pic');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');

    let currentIndex = 0;

    function showSlide(index) {
        slides.forEach((slide, i) => {
            if (i === index) {
                slide.classList.add('active');
                slide.classList.remove('inactive');
            } else if (i < index) {
                slide.classList.add('inactive');
                slide.classList.remove('active');
            } else {
                slide.classList.remove('active', 'inactive');
            }
        });
    }

    nextBtn.addEventListener('click', () => {
        currentIndex = (currentIndex + 1) % slides.length;
        showSlide(currentIndex);
    });

    prevBtn.addEventListener('click', () => {
        currentIndex = (currentIndex - 1 + slides.length) % slides.length;
        showSlide(currentIndex);
    });

    showSlide(currentIndex);
});
