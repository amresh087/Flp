var currentIndex = 0,
  items = $('.container2 div'),
  itemAmt = items.length;

function cycleItems() {
  /*var item = $('.container2 div').eq(currentIndex);
  items.hide();
  item.css('display','inline-block');*/
}

function cycleItems1() {
 var item = $('.container2 div').eq(currentIndex);
  items.hide();
  item.css('display','inline-block');
}

/*var autoSlide = setInterval(function() {
  currentIndex += 1;
  if (currentIndex > itemAmt - 1) {
    currentIndex = 0;
  }
  cycleItems();
}, 3000000000);

$('.next').click(function() {
//  clearInterval(autoSlide);
  currentIndex += 1;
  if (currentIndex > itemAmt - 1) {
    currentIndex = 0;
  }
  cycleItems1();
});

$('.prev').click(function() {
  //clearInterval(autoSlide);
  currentIndex -= 1;
  if (currentIndex < 0) {
    currentIndex = itemAmt - 1;
  }
  cycleItems1();
});*/