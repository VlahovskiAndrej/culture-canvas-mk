function updateStars() {
    const rating = document.querySelector('input[name="score"]:checked').value;
    const stars = document.querySelectorAll('.rating label');

    stars.forEach((star, index) => {
        star.classList.remove('bi-star');
    });

    stars.forEach((star, index) => {
        if (index < rating) {
            star.classList.add('bi-star-fill');
        } else {
            star.classList.add('bi-star');
        }
    });

}