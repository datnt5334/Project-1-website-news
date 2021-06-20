
/* Animate Counter */

const counters = document.querySelectorAll('#counter');
const speed = 200;

counters.forEach(counter => {

    const updateCount = () => {

        const target = +counter.getAttribute('data-target');
        const count = +counter.innerText;

        const inc = target / speed;

        if(count < target) {
            counter.innerText = Math.ceil(count + inc);
            setTimeout(updateCount, 1);
        } else {
            count.innerText = target;
        }
    }

    updateCount();

});

/* check all */

function toggle(source) {
    checkboxes = document.getElementsByName('remove');
    n = checkboxes.length;
    for(var i = 0; i < n; i++) {
        checkboxes[i].checked = source.checked;
    }
}
