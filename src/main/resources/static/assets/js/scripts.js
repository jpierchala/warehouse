$(function () {
    $(".error-text").hide();
    $("#warehouse-product-amount>input").prop("disabled", false);
    $("#sourceSelect").change(function () {
        var selectedOption = $(this).children("option:selected").val();

        if(selectedOption !== undefined){
            if(selectedOption.includes("WAREHOUSE")){
                if(productName !== 'none'){
                    $.get("/product/getProduct", {name: productName, source: selectedOption})
                        .done(function (data) {
                            console.log(data);
                            var successData = data['success'];
                            console.log(successData);
                            if(successData['error'] === undefined){
                                $("#warehouse-product-amount>input").prop("disabled", false);
                                $("#sourceProductId").val(successData['id']);
                                $(".error-text").hide();
                                $(".amount").html(successData['amount']);
                            }else {
                                console.log("no data");
                                $("#warehouse-product-amount>input").prop("disabled", true);
                                $("#sourceProductId").val('');
                                $(".error-text").show();
                                $(".save-product").prop("disabled", true);
                                $(".amount").html('0');
                            }
                        });
                }
            }else {
                $("#warehouse-product-amount>input").prop("disabled", false);
                $(".error-text").hide();
                $(".save-product").prop("disabled", false);
                $(".amount").html('&#8734;');
            }
        }

    })
});