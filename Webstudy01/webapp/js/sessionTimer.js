/**
 * 
 */
	$.fn.sessionTimer=function(timeout){
		const TIMEOUT = timeout;
		var timeArea = this;
		var timer = null;
		var timerJob = null;
		var div1 = null;
		function makeMessageArea(){
			let modalHeader = $("<div>").addClass("modal-header").append($("<h5>").text("세션 연장"));
			let modalBody = $("<div>").addClass("modal-body").append($("<p>").text("세션 연장하시겠습니까?"));
			let yesBtn = $("<button>").addClass("btn btn-primary").text("YES").prop("id", "yesBtn");
			let noBtn = $("<button>").addClass("btn btn-primary").text("NO").prop("id", "noBtn");
			let modalFooter = $("<div>").addClass("modal-footer").append(yesBtn, noBtn);
			let modalContent = $("<div>").addClass("modal-content").append(modalHeader, modalBody, modalFooter);
			div1 = $("<div>").attr({
				"class":"modal fade"
				, "id" :"msgArea"
				, tabindex :"-1"
			}).append($("<div>").addClass("modal-dialog").append(modalContent));
			
			timeArea.after(div1);
			
			div1.modal({show:false});
			
			let clickHandler = function(){
				let id = this.id;
				if(id=="yesBtn"){
					init();
				}
				div1.modal("hide");
			}; 
			yesBtn.on("click", clickHandler);
			noBtn.on("click", clickHandler);
			
		}
		function init(){
			timer = TIMEOUT;
			if(timerJob) clearInterval(timerJob);
			timerJob = setInterval(() => {
				timer--;
				// 2:00
				let min = Math.trunc(timer/60);
				let sec = timer%60;
				timeArea.text( min+":"+sec );
				console.log(timer);
				if(timer<=0) {
					clearInterval(timerJob);
					div1.modal("hide");
				}
			}, 100);
			setTimeout(() => {
				$("#msgArea").modal("show");
				
			}, (TIMEOUT-60)*100);
		}
		makeMessageArea();
		init();
		return this;
	}