<template>
    <div class="introduction-view">
        <div class="top-half">
            <canvas-component :wrapperClass="['particles-canvas-wrapper']">
                <template slot-scope="{mousePos}">
                    <particles-component :mousePos="mousePos"></particles-component>
                </template>
            </canvas-component>
        </div>
        <div class="photo"></div>
        <div class="bottom-half">
            <div class="greeting">
                <div class="flex top">Greetings, my name is Igor Pichugin</div>
                <div class="flex bottom">I am a full-stack developer.</div>
            </div>
            <div @click="$emit('slideDown')" class="arrow-component-wrapper">
                <canvas-component :wrapperClass="['arrow-canvas-wrapper']">
                    <arrow-component></arrow-component>
                </canvas-component>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data: function () {
            return {

            }
        },
        components: {
            CanvasComponent: require('./CanvasComponent.vue').default,
            ArrowComponent: require('./ArrowComponent.vue').default,
            ParticlesComponent: require('./ParticlesComponent.vue').default,
        },
        methods: {

        }
    }
</script>

<style scoped>

</style>