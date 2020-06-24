<template>

    <div :class="wrapperClass">
        <canvas ref="canvas" @mousemove="trackMouse($event)"></canvas>
        <slot :mousePos="mousePos"></slot>
    </div>

</template>

<script>
    export default {
        data: function () {
            return {
                provider: {
                    ctx: null
                },
                mousePos: {
                    x: null,
                    y: null
                }
            }
        },
        props: ['wrapperClass'],
        provide() {
            return {
                provider: this.provider
            }
        },
        mounted() {
            this.provider.ctx = this.$refs['canvas'].getContext('2d');
            this.$refs['canvas'].width = this.$refs['canvas'].parentElement.clientWidth;
            this.$refs['canvas'].height = this.$refs['canvas'].parentElement.clientHeight;
        },
        methods: {
            trackMouse(event) {
                this.mousePos = {
                    x: event.clientX,
                    y: event.clientY
                };
            }
        }
    }
</script>

<style scoped>

</style>