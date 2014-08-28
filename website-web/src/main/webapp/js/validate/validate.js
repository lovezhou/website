(function(a){a.extend(a.fn,{validate:function(b){if(!this.length){b&&b.debug&&window.console&&console.warn("nothing selected, can't validate, returning nothing");return}var c=a.data(this[0],"validator");if(c){return c}c=new a.validator(b,this[0]);a.data(this[0],"validator",c);if(c.settings.onsubmit){this.find("input, button").filter(".cancel").click(function(){c.cancelSubmit=true});this.submit(function(d){if(c.settings.debug){d.preventDefault()}function e(){if(c.settings.submitHandler){c.settings.submitHandler.call(c,c.currentForm);return false}return true}if(c.cancelSubmit){c.cancelSubmit=false;return e()}if(c.form()){if(c.pendingRequest){c.formSubmitted=true;return false}return e()}else{c.focusInvalid();return false}})}return c},valid:function(){if(a(this[0]).is("form")){return this.validate().form()}else{var c=false;var b=a(this[0].form).validate();this.each(function(){c|=b.element(this)});return c}},removeAttrs:function(d){var b={},c=this;a.each(d.split(/\s/),function(e,f){b[f]=c.attr(f);c.removeAttr(f)});return b},rules:function(e,b){var g=this[0];if(e){var d=a.data(g.form,"validator").settings;var i=d.rules;var j=a.validator.staticRules(g);switch(e){case"add":a.extend(j,a.validator.normalizeRule(b));i[g.name]=j;if(b.messages){d.messages[g.name]=a.extend(d.messages[g.name],b.messages)}break;case"remove":if(!b){delete i[g.name];return j}var h={};a.each(b.split(/\s/),function(k,l){h[l]=j[l];delete j[l]});return h}}var f=a.validator.normalizeRules(a.extend({},a.validator.metadataRules(g),a.validator.classRules(g),a.validator.attributeRules(g),a.validator.staticRules(g)),g);if(f.required){var c=f.required;delete f.required;f=a.extend({required:c},f)}return f}});a.extend(a.expr[":"],{blank:function(b){return !a.trim(b.value)},filled:function(b){return !!a.trim(b.value)},unchecked:function(b){return !b.checked}});a.format=function(b,c){if(arguments.length==1){return function(){var d=a.makeArray(arguments);d.unshift(b);return a.format.apply(this,d)}}if(arguments.length>2&&c.constructor!=Array){c=a.makeArray(arguments).slice(1)}if(c.constructor!=Array){c=[c]}a.each(c,function(d,e){b=b.replace(new RegExp("\\{"+d+"\\}","g"),e)});return b};a.validator=function(b,c){this.settings=a.extend({},a.validator.defaults,b);this.currentForm=c;this.init()};a.extend(a.validator,{defaults:{messages:{},groups:{},rules:{},successClass:"txt-success",errorClass:"txt-error",errorTipClass:"tip-error",errorElement:"span",focusInvalid:true,errorContainer:a([]),errorLabelContainer:a([]),onsubmit:true,ignore:[],ignoreTitle:false,onfocusin:function(b){this.lastActive=b;if(this.settings.focusCleanup&&!this.blockFocusCleanup){this.settings.unhighlight&&this.settings.unhighlight.call(this,b,this.settings.errorClass);this.errorsFor(b).hide()}},onfocusout:function(b){if(!this.checkable(b)&&(b.name in this.submitted||!this.optional(b))){this.element(b)}},onkeyup:function(b){if(b.name in this.submitted||b==this.lastElement){this.element(b)}},onclick:function(b){if(b.name in this.submitted){this.element(b)}},highlight:function(c,b,d){a(c).parent().addClass(b).removeClass(d)},unhighlight:function(c,b,d){a(c).parent().removeClass(b).addClass(d)}},setDefaults:function(b){a.extend(a.validator.defaults,b)},messages:{required:"This field is required.",remote:"Please fix this field.",email:"Please enter a valid email address.",url:"Please enter a valid URL.",date:"Please enter a valid date.",dateISO:"Please enter a valid date (ISO).",dateDE:"Bitte geben Sie ein g\u7709ltiges Datum ein.",number:"Please enter a valid number.",numberDE:"Bitte geben Sie eine Nummer ein.",digits:"Please enter only digits",creditcard:"Please enter a valid credit card number.",equalTo:"Please enter the same value again.",accept:"Please enter a value with a valid extension.",maxlength:a.format("Please enter no more than {0} characters."),minlength:a.format("Please enter at least {0} characters."),rangelength:a.format("Please enter a value between {0} and {1} characters long."),range:a.format("Please enter a value between {0} and {1}."),max:a.format("Please enter a value less than or equal to {0}."),min:a.format("Please enter a value greater than or equal to {0}.")},autoCreateRanges:false,prototype:{init:function(){this.labelContainer=a(this.settings.errorLabelContainer);this.errorContext=this.labelContainer.length&&this.labelContainer||a(this.currentForm);this.containers=a(this.settings.errorContainer).add(this.settings.errorLabelContainer);this.submitted={};this.valueCache={};this.pendingRequest=0;this.pending={};this.invalid={};this.reset();var b=(this.groups={});a.each(this.settings.groups,function(e,f){a.each(f.split(/\s/),function(h,g){b[g]=e})});var d=this.settings.rules;a.each(d,function(e,f){d[e]=a.validator.normalizeRule(f)});function c(f){var e=a.data(this[0].form,"validator");e.settings["on"+f.type]&&e.settings["on"+f.type].call(e,this[0])}a(this.currentForm).delegate("focusin focusout keyup",":text, :password, :file, select, textarea",c).delegate("click",":radio, :checkbox",c);if(this.settings.invalidHandler){a(this.currentForm).bind("invalid-form.validate",this.settings.invalidHandler)}},form:function(){this.checkForm();a.extend(this.submitted,this.errorMap);this.invalid=a.extend({},this.errorMap);if(!this.valid()){a(this.currentForm).triggerHandler("invalid-form",[this])}this.showErrors();return this.valid()},checkForm:function(){this.prepareForm();for(var b=0,c=(this.currentElements=this.elements());c[b];b++){this.check(c[b])}return this.valid()},element:function(c){c=this.clean(c);this.lastElement=c;this.prepareElement(c);this.currentElements=a(c);var b=this.check(c);if(b){delete this.invalid[c.name]}else{this.invalid[c.name]=true}if(!this.numberOfInvalids()){this.toHide=this.toHide.add(this.containers)}this.showErrors();return b},showErrors:function(c){if(c){a.extend(this.errorMap,c);this.errorList=[];for(var b in c){this.errorList.push({message:c[b],element:this.findByName(b)[0]})}this.successList=a.grep(this.successList,function(d){return !(d.name in c)})}this.settings.showErrors?this.settings.showErrors.call(this,this.errorMap,this.errorList):this.defaultShowErrors()},resetForm:function(){if(a.fn.resetForm){a(this.currentForm).resetForm()}this.submitted={};this.prepareForm();this.hideErrors();this.elements().removeClass(this.settings.errorClass)},numberOfInvalids:function(){return this.objectLength(this.invalid)},objectLength:function(d){var c=0;for(var b in d){c++}return c},hideErrors:function(){this.addWrapper(this.toHide).hide()},valid:function(){return this.size()==0},size:function(){return this.errorList.length},focusInvalid:function(){if(this.settings.focusInvalid){try{a(this.findLastActive()||this.errorList.length&&this.errorList[0].element||[]).filter(":visible").focus()}catch(b){}}},findLastActive:function(){var b=this.lastActive;return b&&a.grep(this.errorList,function(c){return c.element.name==b.name}).length==1&&b},elements:function(){var c=this,b={};return a([]).add(this.currentForm.elements).filter(":input").not(":submit, :reset, :image, [disabled]").not(this.settings.ignore).filter(function(){!this.name&&c.settings.debug&&window.console&&console.error("%o has no name assigned",this);if(this.name in b||!c.objectLength(a(this).rules())){return false}b[this.name]=true;return true})},clean:function(b){return a(b)[0]},errors:function(){return a(this.settings.errorElement+"."+this.settings.errorTipClass,this.errorContext)},reset:function(){this.successList=[];this.errorList=[];this.errorMap={};this.toShow=a([]);this.toHide=a([]);this.formSubmitted=false;this.currentElements=a([])},prepareForm:function(){this.reset();this.toHide=this.errors().add(this.containers)},prepareElement:function(b){this.reset();this.toHide=this.errorsFor(b)},check:function(c){c=this.clean(c);if(this.checkable(c)){c=this.findByName(c.name)[0]}var h=a(c).rules();var d=false;for(method in h){var g={method:method,parameters:h[method]};try{var b=a.validator.methods[method].call(this,c.value.replace(/\r/g,""),c,g.parameters);if(b=="dependency-mismatch"){d=true;continue}d=false;if(b=="pending"){this.toHide=this.toHide.not(this.errorsFor(c));return}if(!b){this.formatAndAdd(c,g);return false}}catch(f){this.settings.debug&&window.console&&console.log("exception occured when checking element "+c.id+", check the '"+g.method+"' method");throw f}}if(d){return}if(this.objectLength(h)){this.successList.push(c)}return true},customMetaMessage:function(b,d){if(!a.metadata){return}var c=this.settings.meta?a(b).metadata()[this.settings.meta]:a(b).metadata();return c&&c.messages&&c.messages[d]},customMessage:function(c,d){var b=this.settings.messages[c];return b&&(b.constructor==String?b:b[d])},findDefined:function(){for(var b=0;b<arguments.length;b++){if(arguments[b]!==undefined){return arguments[b]}}return undefined},defaultMessage:function(b,c){return this.findDefined(this.customMessage(b.name,c),this.customMetaMessage(b,c),!this.settings.ignoreTitle&&b.title||undefined,a.validator.messages[c],"<strong>Warning: No message defined for "+b.name+"</strong>")},formatAndAdd:function(b,d){var c=this.defaultMessage(b,d.method);if(typeof c=="function"){c=c.call(this,d.parameters,b)}this.errorList.push({message:c,element:b});this.errorMap[b.name]=c;this.submitted[b.name]=c},addWrapper:function(b){if(this.settings.wrapper){b=b.add(b.parents(this.settings.wrapper))}return b},defaultShowErrors:function(){for(var c=0;this.errorList[c];c++){var b=this.errorList[c];this.settings.highlight&&this.settings.highlight.call(this,b.element,this.settings.errorClass,this.settings.successClass);this.showLabel(b.element,b.message)}if(this.errorList.length){this.toShow=this.toShow.add(this.containers)}if(this.settings.success){for(var c=0;this.successList[c];c++){this.showLabel(this.successList[c])}}if(this.settings.unhighlight){for(var c=0,d=this.validElements();d[c];c++){this.settings.unhighlight.call(this,d[c],this.settings.errorClass,this.settings.successClass)}}this.toHide=this.toHide.not(this.toShow);this.hideErrors();this.addWrapper(this.toShow).show()},validElements:function(){return this.currentElements.not(this.invalidElements())},invalidElements:function(){return a(this.errorList).map(function(){return this.element})},showLabel:function(c,d){var b=this.errorsFor(c);this.errorsFor(c).next().hide();if(b.length){b.removeClass().addClass(this.settings.errorTipClass);b.attr("generated")&&b.html(d)}else{b=a("<"+this.settings.errorElement+"/>").attr({"for":this.idOrName(c),generated:true}).addClass(this.settings.errorTipClass).html(d||"");if(this.settings.wrapper){b=b.hide().show().wrap("<"+this.settings.wrapper+"/>").parent()}if(!this.labelContainer.append(b).length){this.settings.errorPlacement?this.settings.errorPlacement(b,a(c)):b.insertAfter(a(c).parent())}}if(!d&&this.settings.success){b.text("");typeof this.settings.success=="string"?b.addClass(this.settings.success):this.settings.success(b)}this.toShow=this.toShow.add(b)},errorsFor:function(b){return this.errors().filter("[for='"+this.idOrName(b)+"']")},idOrName:function(b){return this.groups[b.name]||(this.checkable(b)?b.name:b.id||b.name)},checkable:function(b){return/radio|checkbox/i.test(b.type)},findByName:function(b){var c=this.currentForm;return a(document.getElementsByName(b)).map(function(d,e){return e.form==c&&e.name==b&&e||null})},getLength:function(c,b){switch(b.nodeName.toLowerCase()){case"select":return a("option:selected",b).length;case"input":if(this.checkable(b)){return this.findByName(b.name).filter(":checked").length}}return c.length},depend:function(c,b){return this.dependTypes[typeof c]?this.dependTypes[typeof c](c,b):true},dependTypes:{"boolean":function(c,b){return c},string:function(c,b){return !!a(c,b.form).length},"function":function(c,b){return c(b)}},optional:function(b){return !a.validator.methods.required.call(this,a.trim(b.value),b)&&"dependency-mismatch"},startRequest:function(b){if(!this.pending[b.name]){this.pendingRequest++;this.pending[b.name]=true}},stopRequest:function(b,c){this.pendingRequest--;if(this.pendingRequest<0){this.pendingRequest=0}delete this.pending[b.name];if(c&&this.pendingRequest==0&&this.formSubmitted&&this.form()){a(this.currentForm).submit()}else{if(!c&&this.pendingRequest==0&&this.formSubmitted){a(this.currentForm).triggerHandler("invalid-form",[this])}}},previousValue:function(b){return a.data(b,"previousValue")||a.data(b,"previousValue",previous={old:null,valid:true,message:this.defaultMessage(b,"remote")})}},classRuleSettings:{required:{required:true},email:{email:true},url:{url:true},date:{date:true},dateISO:{dateISO:true},dateDE:{dateDE:true},number:{number:true},numberDE:{numberDE:true},digits:{digits:true},creditcard:{creditcard:true}},addClassRules:function(b,c){b.constructor==String?this.classRuleSettings[b]=c:a.extend(this.classRuleSettings,b)},classRules:function(c){var d={};var b=a(c).attr("class");b&&a.each(b.split(" "),function(){if(this in a.validator.classRuleSettings){a.extend(d,a.validator.classRuleSettings[this])}});return d},attributeRules:function(c){var e={};var b=a(c);for(method in a.validator.methods){var d=b.attr(method);if(d){e[method]=d}}if(e.maxlength&&/-1|2147483647|524288/.test(e.maxlength)){delete e.maxlength}return e},metadataRules:function(b){if(!a.metadata){return{}}var c=a.data(b.form,"validator").settings.meta;return c?a(b).metadata()[c]:a(b).metadata()},staticRules:function(c){var d={};var b=a.data(c.form,"validator");if(b.settings.rules){d=a.validator.normalizeRule(b.settings.rules[c.name])||{}}return d},normalizeRules:function(c,b){a.each(c,function(f,e){if(e===false){delete c[f];return}if(e.param||e.depends){var d=true;switch(typeof e.depends){case"string":d=!!a(e.depends,b.form).length;break;case"function":d=e.depends.call(b,b);break}if(d){c[f]=e.param!==undefined?e.param:true}else{delete c[f]}}});a.each(c,function(d,e){c[d]=a.isFunction(e)?e(b):e});a.each(["minlength","maxlength","min","max"],function(){if(c[this]){c[this]=Number(c[this])}});a.each(["rangelength","range"],function(){if(c[this]){c[this]=[Number(c[this][0]),Number(c[this][1])]}});if(a.validator.autoCreateRanges){if(c.min&&c.max){c.range=[c.min,c.max];delete c.min;delete c.max}if(c.minlength&&c.maxlength){c.rangelength=[c.minlength,c.maxlength];delete c.minlength;delete c.maxlength}}if(c.messages){delete c.messages}return c},normalizeRule:function(c){if(typeof c=="string"){var b={};a.each(c.split(/\s/),function(){b[this]=true});c=b}return c},addMethod:function(b,d,c){a.validator.methods[b]=d;a.validator.messages[b]=c;if(d.length<3){a.validator.addClassRules(b,a.validator.normalizeRule(b))}},methods:{required:function(d,c,e){if(!this.depend(e,c)){return"dependency-mismatch"}switch(c.nodeName.toLowerCase()){case"select":var b=a("option:selected",c);return b.length>0&&(c.type=="select-multiple"||(a.browser.msie&&!(b[0].attributes.value.specified)?b[0].text:b[0].value).length>0);case"input":if(this.checkable(c)){return this.getLength(d,c)>0}default:return a.trim(d).length>0}},remote:function(f,c,g){if(this.optional(c)){return"dependency-mismatch"}var d=this.previousValue(c);if(!this.settings.messages[c.name]){this.settings.messages[c.name]={}}this.settings.messages[c.name].remote=typeof d.message=="function"?d.message(f):d.message;g=typeof g=="string"&&{url:g}||g;if(d.old!==f){d.old=f;var b=this;this.startRequest(c);var e={};e[c.name]=f;a.ajax(a.extend(true,{url:g,mode:"abort",port:"validate"+c.name,dataType:"json",data:e,success:function(i){if(i){var h=b.formSubmitted;b.prepareElement(c);b.formSubmitted=h;b.successList.push(c);b.showErrors()}else{var j={};j[c.name]=i||b.defaultMessage(c,"remote");b.showErrors(j)}d.valid=i;b.stopRequest(c,i)}},g));return"pending"}else{if(this.pending[c.name]){return"pending"}}return d.valid},minlength:function(c,b,d){return this.optional(b)||this.getLength(a.trim(c),b)>=d},maxlength:function(c,b,d){return this.optional(b)||this.getLength(a.trim(c),b)<=d},rangelength:function(d,b,e){var c=this.getLength(a.trim(d),b);return this.optional(b)||(c>=e[0]&&c<=e[1])},min:function(c,b,d){return this.optional(b)||c>=d},max:function(c,b,d){return this.optional(b)||c<=d},range:function(c,b,d){return this.optional(b)||(c>=d[0]&&c<=d[1])},email:function(c,b){return this.optional(b)||/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(c)},url:function(c,b){return this.optional(b)||/^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(c)},date:function(c,b){return this.optional(b)||!/Invalid|NaN/.test(new Date(c))},dateISO:function(c,b){return this.optional(b)||/^\d{4}[\/-]\d{1,2}[\/-]\d{1,2}$/.test(c)},dateDE:function(c,b){return this.optional(b)||/^\d\d?\.\d\d?\.\d\d\d?\d?$/.test(c)},number:function(c,b){return this.optional(b)||/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(c)},numberDE:function(c,b){return this.optional(b)||/^-?(?:\d+|\d{1,3}(?:\.\d{3})+)(?:,\d+)?$/.test(c)},digits:function(c,b){return this.optional(b)||/^\d+$/.test(c)},creditcard:function(f,c){if(this.optional(c)){return"dependency-mismatch"}if(/[^0-9-]+/.test(f)){return false}var g=0,e=0,b=false;f=f.replace(/\D/g,"");for(n=f.length-1;n>=0;n--){var d=f.charAt(n);var e=parseInt(d,10);if(b){if((e*=2)>9){e-=9}}g+=e;b=!b}return(g%10)==0},accept:function(c,b,d){d=typeof d=="string"?d:"png|jpe?g|gif";return this.optional(b)||c.match(new RegExp(".("+d+")$","i"))},equalTo:function(c,b,d){return c==a(d).val()}}})})(jQuery);(function(c){var b=c.ajax;var a={};c.ajax=function(e){e=c.extend(e,c.extend({},c.ajaxSettings,e));var d=e.port;if(e.mode=="abort"){if(a[d]){a[d].abort()}return(a[d]=b.apply(this,arguments))}return b.apply(this,arguments)}})(jQuery);(function(a){a.each({focus:"focusin",blur:"focusout"},function(c,b){a.event.special[b]={setup:function(){if(a.browser.msie){return false}this.addEventListener(c,a.event.special[b].handler,true)},teardown:function(){if(a.browser.msie){return false}this.removeEventListener(c,a.event.special[b].handler,true)},handler:function(d){arguments[0]=a.event.fix(d);arguments[0].type=b;return a.event.handle.apply(this,arguments)}}});a.extend(a.fn,{delegate:function(d,c,b){return this.bind(d,function(e){var f=a(e.target);if(f.is(c)){return b.apply(f,arguments)}})},triggerEvent:function(b,c){return this.triggerHandler(b,[a.event.fix({type:b,target:c})])}})})(jQuery);
(function($){$.extend({metadata:{defaults:{type:"class",name:"metadata",cre:/({.*})/,single:"metadata"},setType:function(type,name){this.defaults.type=type;this.defaults.name=name},get:function(elem,opts){var settings=$.extend({},this.defaults,opts);if(!settings.single.length){settings.single="metadata"}var data=$.data(elem,settings.single);if(data){return data}data="{}";if(settings.type=="class"){var m=settings.cre.exec(elem.className);if(m){data=m[1]}}else{if(settings.type=="elem"){if(!elem.getElementsByTagName){return undefined}var e=elem.getElementsByTagName(settings.name);if(e.length){data=$.trim(e[0].innerHTML)}}else{if(elem.getAttribute!=undefined){var attr=elem.getAttribute(settings.name);if(attr){data=attr}}}}if(data.indexOf("{")<0){data="{"+data+"}"}data=eval("("+data+")");$.data(elem,settings.single,data);return data}}});$.fn.metadata=function(opts){return $.metadata.get(this[0],opts)}})(jQuery);
jQuery.extend(jQuery.validator.messages, {
	required: "此项不能为空",
	remote: "请修正该字段",
	email: "请输入正确格式的电子邮件",
	url: "请输入合法的网址",
	date: "请输入合法的日期",
	dateISO: "请输入合法的日期 (ISO).",
	number: "请输入合法的数字",
	digits: "只能输入整数",
	creditcard: "请输入合法的信用卡号",
	equalTo: "请再次输入相同的值",
	accept: "请输入拥有合法后缀名的字符串",
	maxlength: $.format("请输入一个 长度最多是 {0} 的字符串"),
	minlength: $.format("请输入一个 长度最少是 {0} 的字符串"),
	rangelength: $.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
	range: $.format("请输入一个介于 {0} 和 {1} 之间的值"),
	max: $.format("请输入一个最大为{0} 的值"),
	min: $.format("请输入一个最小为{0} 的值")
});

var jessrun = typeof $ === "function" ? window.$: {};





//页面提示正则
jessrun.regEx ={
	Strs:/^[\u0391-\uFFE5\w]+$/, //中文字、英文字母、数字和下划线
	NumEn:/^[_A-Za-z0-9]+$/,//检查数字英文
	IntZero:/^[0-9]*[1-9][0-9]*$/,//检查非0正整数
	Int6:/^\d{6}$/,//检查6位数字
	Amount:/^(0\.\d{1,2}|[1-9]\d{0,8}(\.\d{1,2})?)$/,//检查金额数字 0.00 和 小数的后两位
	Tel:/^1(3|4|5|8)\d{9}$/,//检查手机号码
	Mobile:/^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/,//检查固定电话 //0471-1234567
	PostCode:/^\d{6}$/,//检查中国邮政编码
	Email:/^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*$/,//检查电子邮件
	ChineWord:/^[\u4e00-\u9fa5]+$/,//检查汉字
	SimpCardId:/^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X|x)?$/,//简单检查身份证方法
	ChineseEnNum:/^[\u4e00-\u9fa5a-zA-Z0-9]+$/,//检查中文和英文 数字
	Time:/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/ //检查时间格式YYYY-MM-DD
};

//身份证
jessrun.personCard =function(idcard){
	var Errors=new Array(
	"OK",
	"身份证号码位数不对!",
	"身份证号码出生日期超出范围或含有非法字符!",
	"身份证号码校验错误!",
	"身份证地区非法!"
	);
	var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
	var idcard,Y,JYM;
	var S,M;
	var idcard_array = new Array();
	idcard_array = idcard.split("");
	if(area[parseInt(idcard.substr(0,2))]==null){
		return Errors[4];
	}
	switch(idcard.length){
	case 15:
		if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
		ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
		} else {
		ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
		}
		if(ereg.test(idcard))
			return Errors[0];
		else
			return Errors[2];
		break;
	case 18:
		if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){
		ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
	} else {
		ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
	}
	if(ereg.test(idcard)){
		S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
		+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
		+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
		+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
		+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
		+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
		+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
		+ parseInt(idcard_array[7]) * 1
		+ parseInt(idcard_array[8]) * 6
		+ parseInt(idcard_array[9]) * 3 ;
		Y = S % 11;
		M = "F";
		JYM = "10X98765432";
		M = JYM.substr(Y,1);
		if(M == idcard_array[17])
			return Errors[0];
		else
			return Errors[3];
	}else{
		return Errors[2];
	}
		break;
	default:
		return Errors[1];
		break;
	}
}
//时间分割
jessrun.toDate=function (str){
	var sd=str.split("-");
	return new Date(sd[0],sd[1],sd[2]);
}

//字符串字节 $("#id").val().len() 长度获取
String.prototype.len = function(){
   return this.replace(/[^\x00-\xff]/g, "xx").length;
}
//检查字符
jessrun.isStrs=function(s){
	if(jessrun.regEx.Strs.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查数字英文
jessrun.isNumEn =function(s){
	if(jessrun.regEx.NumEn.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查非0正整数
jessrun.isIntZero=function(s){
	if(jessrun.regEx.IntZero.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查纯数字
jessrun.isInt=function(s){
	if(jessrun.regEx.Int.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查6位数字
jessrun.isInt6=function(s){
	if(jessrun.regEx.Int6.test(s)){
		return true;
	}else{
		return false;
	}
}

//检查金额数字 0.00 和 小数的后两位
jessrun.isAmount=function (s){
	if(jessrun.regEx.Amount.test(s)&&s!=0.00){
		return true;
	}else{
		return false;
	}
}

//检查手机号码
jessrun.isTel=function (s){
	if(jessrun.regEx.Tel.test(s)){
		return true;
	}else{
		return false;
	}
}

//检查固定电话 //0471-1234567
jessrun.isMobile=function(s){
   if(jessrun.regEx.Mobile.test(s)){
	   return true;
   }else{
	  return false;
	}
}
//检查中国邮政编码
jessrun.isPostCode=function (s){
   if(jessrun.regEx.PostCode.test(s)){
	   return true;
   }else{
	  return false;
	}
}
//检查电子邮件
jessrun.isEmail=function (s){
	if(jessrun.regEx.Email.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查密码 6-16位英文字母、数字或者符号至少2种的组合的登录密码
jessrun.isPwd=function(s){
	var reg=/^[\a-\z\A-\Z0-9\`\-\=\,\.\/\;\'\[\]\\~\!\@\#\$\%\^\&\*\(\)\_\+\{\}\|\:\"\<\>\?\\\@\.]+$/;
	var onlyabc=/^[A-Za-z]+$/;
	var onlynum=/^[0-9]+$/;
	var onlyspc =/^[\`\-\=\,\.\/\;\'\[\]\~\!\@\#\$\%\^\&\*\(\)\_\+\{\}\|\:"\<\>\?\@]+$/;
	if(onlynum.test(s)){
		return false;
	}
	if(onlyabc.test(s)){
		return false;
	}
	if(onlyspc.test(s)){
		return false;
	}
	if(s.length<6||s.length>16){
		return false;
	}else if(reg.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查汉字
jessrun.isChineWord=function(s){
	if(jessrun.regEx.ChineWord.test(s)){
		return true;
	}else{
		return false;
	}
}
//检查身份证
jessrun.isCardId=function(s){
	var s=jessrun.personCard(s);
	if(s=="OK"||s=="ok"){
		return true;
	}else {
		return false;
	}
}
//简单检查身份证方法
jessrun.isSimpCardId=function(s){
	if(jessrun.regEx.SimpCardIdtest(s)){
		return true;
	}else{
		return false;
	}
}
//检查中文和英文
jessrun.isChineseEnNum=function(s){
	if(jessrun.regEx.ChineseEnNum.test(s)){
		return true;
	}else{
		return false;
	}
}

//检查时间格式YYYY-MM-DD
jessrun.isDateTime=function(s){
	if(jessrun.regEx.Time.test(s)){
		return true;
	}else{
		return false;
	}
}

/*
	公用检查方法，以后直接引用
*/

//检查登录名（邮件或手机号码）
jessrun.chkUserName=function (v){
	if(!jessrun.isEmail(v)&&!jessrun.isTel(v)){
		return false
	}else{
		return true;
	}
}
//检查登录密码
jessrun.chkUserPwd = function(v){
	if(!jessrun.isPwd(v)){
		return false;
	}else{
		return true;
	}
}

//检查图片验证码
jessrun.chkImgCode =function (v){
	if(!jessrun.isInt6(v)){
		return false
	}else{
		return true;
	}
}
//检查是否勾选协议
jessrun.chkServie=function (v){
	if($(v).attr("checked")==false){
		return false;
	}else{
		return true;
	}
}

//检查手机号
jessrun.chkTel=function (v){
	if(!jessrun.isTel(v)){
		return false;
	}else{
		return true;
	}
}
//检查固定电话
jessrun.chkMobile=function (v){
	if(!jessrun.isMobile(v)){
		return false;
	}else{
		return true;
	}
}
//检查邮箱
jessrun.chkEmail=function (v){
	if(!jessrun.isEmail(v)){
		return false;
	}else{
		return true;
	}
}

//检查邮政编码
jessrun.chkPostCode=function (v){
	if(!jessrun.isPostCode(v)){
		return false;
	}else{
		return true;
	}
}
//检查中文姓名
jessrun.chkChineseName=function (v){
	if(!jessrun.isChineWord(v)){
		return false;
	}else{
		return true;
	}
}
//检查公司名称
jessrun.chkCompanyName=function (v){
	if(!jessrun.isChineseEnNum(v)){
		return false;
	}else{
		return true;
	}
}
//检查身份证号
jessrun.chkCardId=function (v){
	if(!jessrun.isCardId(v)){
		return false;
	}else{
		return true;
	}
}
//检查金额
jessrun.chkAmount=function (v){
	if(!jessrun.isAmount(v)){
		return false;
	}else{
		return true;
	}
}

//检查数量
jessrun.chkTotal=function (v){
	if(!jessrun.isIntZero(v)){
		return false;
	}else{
		return true;
	}
}

//检查radio checkbox是否选择
jessrun.chkChkBoxRadio = function(o){
	var item = $("[name="+o+"]:checked");
	var len=item.length;
	if(len<0){
		return false;
	}else{
		return true;
	}
}

//检查Select是否选择
jessrun.chkSelected = function(v){
	if(v==""){
		return false;
	}else{
		return true;
	}
}


//检查银行卡号
jessrun.chkBankNo=function (v){
	if(!jessrun.isIntZero(v)){
		return false;
	}else{
		return true;
	}
}

//检查银行卡开户行
jessrun.chkBankName=function (v){
	if(!jessrun.isStrs(v)){
		return false;
	}else{
		return true;
	}
}
