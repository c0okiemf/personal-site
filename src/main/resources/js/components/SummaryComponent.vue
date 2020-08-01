<template>
    <div class="summary-view">
        <div @click="$emit('slideUp')" class="arrow-component-wrapper top">
            <div class="arrow-name">Home</div>
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
                        <div class="f-item">Symfony</div>
                        <div class="f-item">Bitrix</div>
                        <div class="f-item">WordPress</div>
                    </div>
                </div>
                <div class="item">
                    <div class="language">Java</div>
                    <div class="frameworks">
                        <div class="f-item">Spring Boot</div>
                    </div>
                </div>
                <div class="item">
                    <div class="language">JavaScript</div>
                    <div class="frameworks">
                        <div class="f-item">Vue</div>
                        <div class="f-item">React</div>
                        <div class="f-item">Marionette</div>
                    </div>
                </div>
                <div class="item">
                    <div class="language">Elm</div>
                </div>
                <div class="item">
                    <div class="language">CSS</div>
                    <div class="frameworks">
                        <div class="f-item">Sass/Scss</div>
                    </div>
                </div>
                <div class="item">
                    <div class="language">Other</div>
                    <div class="frameworks">
                        <div class="f-item">Testing</div>
                        <div class="f-item">Linux</div>
                        <div class="f-item">Docker</div>
                        <div class="f-item">CI</div>
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
                    I am a full-stack web developer experienced in all stages of the application lifecycle.
                    I worked with UI (implementing responsive designs, SPAs), worked on setting up continuous
                    integration for a very heavyweight website with many external dependencies, actively supplement
                    code with testing. I also have experience in designing and implementing database structures used
                    in real-world business applications.
                    <br>
                    <br>
                    I have a solid understanding of what my code does and why and double-check it so that my colleagues
                    don't have any trouble understanding it either. I am aware of common OOP patterns and put them
                    in good use where applicable.
                    <br>
                    <br>
                    In my spare time, I practice functional programming with Elm and learn game development.
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
            <div class="arrow-name">Contact me</div>
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