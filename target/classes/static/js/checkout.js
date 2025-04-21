
document.addEventListener('DOMContentLoaded', function () {
    // Elements
    const codRadio = document.getElementById('cod');
    const onlineRadio = document.getElementById('online');
    const paymentOptions = document.querySelector('.payment-option');
    const creditCardDetails = document.getElementById('credit-card-details');
    const debitCardDetails = document.getElementById('debit-card-details');

    const paymentRadios = document.querySelectorAll('input[name="paymentMethod"]');

    const differentAddressCheckbox = document.getElementById("differentAddressCheckbox");
    const shippingAddressForm = document.getElementById("shippingAddressForm");

    // Toggle payment options visibility
    function togglePaymentOptions() {
        if (onlineRadio.checked) {
            paymentOptions.classList.remove('hidden');
        } else {
            paymentOptions.classList.add('hidden');
            creditCardDetails.classList.add('hidden');
            debitCardDetails.classList.add('hidden');
        }
    }

    // Handle online/cod toggle
    codRadio.addEventListener('change', togglePaymentOptions);
    onlineRadio.addEventListener('change', togglePaymentOptions);

    // Show appropriate card form
    paymentRadios.forEach((radio) => {
        radio.addEventListener('change', function () {
            creditCardDetails.classList.add('hidden');
            debitCardDetails.classList.add('hidden');

            if (radio.value === 'Credit Card') {
                creditCardDetails.classList.remove('hidden');
            } else if (radio.value === 'Debit Card') {
                debitCardDetails.classList.remove('hidden');
            }
        });
    });

    // Toggle shipping address form
    differentAddressCheckbox.addEventListener("change", function () {
        shippingAddressForm.classList.toggle("hidden", !this.checked);
    });
});




/*
// JavaScript to manage payment method selection
document.addEventListener('DOMContentLoaded', function () {
    const onlinePaymentRadio = document.getElementById('online');
    const paymentOptions = document.querySelector('.payment-option');
    const creditCardDetails = document.getElementById('credit-card-details');
    const debitCardDetails = document.getElementById('debit-card-details');

    // Show/hide payment options based on selection
    onlinePaymentRadio.addEventListener('change', function () {
        if (onlinePaymentRadio.checked) {
            paymentOptions.classList.remove('hidden');
        }
    });

    const codRadio = document.getElementById('cod');
    codRadio.addEventListener('change', function () {
        paymentOptions.classList.add('hidden');
    });

    // Show card details based on payment method selected
    const paymentRadios = document.querySelectorAll('input[name="paymentMethod"]');
    paymentRadios.forEach((radio) => {
        radio.addEventListener('change', function () {
            if (radio.value === 'Credit Card') {
                creditCardDetails.classList.remove('hidden');
                debitCardDetails.classList.add('hidden');
            } else if (radio.value === 'Debit Card') {
                debitCardDetails.classList.remove('hidden');
                creditCardDetails.classList.add('hidden');
            } else {
                creditCardDetails.classList.add('hidden');
                debitCardDetails.classList.add('hidden');
            }
        });
    });
});

document.addEventListener("DOMContentLoaded", function () {
        const checkbox = document.getElementById("differentAddressCheckbox");
        const form = document.getElementById("shippingAddressForm");

        checkbox.addEventListener("change", function () {
            if (checkbox.checked) {
                form.classList.remove("hidden");
            } else {
                form.classList.add("hidden");
            }
        });
    });

/**/
/*
document.addEventListener('DOMContentLoaded', function () {
    const onlineRadio = document.getElementById('online');
    const codRadio = document.getElementById('cod');
    const paymentOptions = document.querySelector('.payment-option');
    const creditCardDetails = document.getElementById('credit-card-details');
    const debitCardDetails = document.getElementById('debit-card-details');

    // Toggle card options based on online/cod
    function togglePaymentOptions() {
        if (onlineRadio.checked) {
            paymentOptions.classList.remove('hidden');
        } else {
            paymentOptions.classList.add('hidden');
            creditCardDetails.classList.add('hidden');
            debitCardDetails.classList.add('hidden');
        }
    }

    onlineRadio.addEventListener('change', togglePaymentOptions);
    codRadio.addEventListener('change', togglePaymentOptions);

    // Show card-specific fields
    document.querySelectorAll('input[name="paymentMethod"]').forEach((radio) => {
        radio.addEventListener('change', () => {
            creditCardDetails.classList.add('hidden');
            debitCardDetails.classList.add('hidden');

            if (radio.checked && radio.id === 'credit-card') {
                creditCardDetails.classList.remove('hidden');
            } else if (radio.checked && radio.id === 'debit-card') {
                debitCardDetails.classList.remove('hidden');
            }
        });
    });
});
/**/ 
