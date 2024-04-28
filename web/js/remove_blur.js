let rm_labels = document.querySelectorAll('.remove_label');


rm_labels.forEach(item =>{
    item.addEventListener('click',function (){
        item.classList.toggle('blur');
        item.classList.toggle('before_blur');
    });
})
