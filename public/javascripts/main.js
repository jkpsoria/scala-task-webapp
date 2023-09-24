
const datetimeInput = document.getElementsByClassName('datetimeInput');

datetimeInput.addEventListener('input', function () {
    this.value = this.value.replace('T', ' ');
});