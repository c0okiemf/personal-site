<template>
    <div class="summary-view">
        <div @click="$emit('slideUp')" class="arrow-component-wrapper top">
            <canvas-component :wrapperClass="['arrow-canvas-wrapper']">
                <arrow-component></arrow-component>
            </canvas-component>
        </div>
        <div class="left-block">
            <div class="languages">
                <div class="head">
                    Tech
                </div>
                <div class="item">
                    <div class="language">PHP</div>
                    <div class="frameworks">
                        <div class="f-item">Laravel</div>
                        <div class="f-item">Bitrix</div>
                    </div>
                </div>
                <div class="item">
                    <div class="language">Java</div>
                    <div class="frameworks">
                        <div class="f-item">Spring</div>
                    </div>
                </div>
                <div class="item">C#</div>
                <div class="item">
                    <div class="language">JavaScript</div>
                    <div class="frameworks">
                        <div class="f-item">Vue</div>
                        <div class="f-item">React</div>
                        <div class="f-item">Marionette</div>
                        <div class="f-item">JQuery</div>
                    </div>
                </div>
                <div class="item">
                    <div class="language">CSS</div>
                    <div class="frameworks">
                        <div class="f-item">Sass/Scss</div>
                    </div>
                </div>
                <div class="item">
                    <div class="language">Misc</div>
                    <div class="frameworks">
                        <div class="f-item">Docker</div>
                        <div class="f-item">CI</div>
                        <div class="f-item">Linux</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="main-block">
            <div class="summary-block">
                <div class="head">
                    Summary
                </div>
                <div class="text">
                    My greatest passion is solving complex problems elegantly and concisely.
                    I deeply enjoy being a software developer and appreciate doing what I love for a living.
                </div>
            </div>
            <div class="links-block">
                <div class="head">
                    Links
                </div>
                <div class="links">
                    <a href="https://github.com/c0okiemf" target="_blank">
                        <img src="img/github.png" alt="">
                    </a>
                    <a href="https://www.linkedin.com/in/pichugin-igor/" target="_blank">
                        <img src="img/linkedin.png" alt="">
                    </a>
                </div>
            </div>
        </div>
        <div @click="$emit('slideDown')" class="arrow-component-wrapper">
            <canvas-component :wrapperClass="['arrow-canvas-wrapper']">
                <arrow-component></arrow-component>
            </canvas-component>
        </div>
    </div>
</template>

<script>
    export default {
        data: function () {
            return {

            }
        },
        activated() {
            document.querySelectorAll('.item').forEach((el, i) => {
                setTimeout(() => {
                    el.animate([
                        {transform: 'translateX(-20vw)'},
                        {transform: 'unset'}
                    ], {
                        duration: 1000,
                        fill: 'forwards'
                    });
                }, 100 * i);
            });
        },
        deactivated() {
            document.querySelectorAll('.item').forEach((el, i) => {
                setTimeout(() => {
                    el.animate([
                        {transform: 'unset'},
                        {transform: 'translateX(-20vw)'}
                    ], {
                        duration: 1000,
                        fill: 'forwards'
                    });
                }, 100 * i);
            });
        },
        components: {
            CanvasComponent: require('./CanvasComponent.vue').default,
            ArrowComponent: require('./ArrowComponent.vue').default,
        }
    }
</script>

<style lang="sass" scoped>

.item
    transform: translateX(-20vw)


</style>