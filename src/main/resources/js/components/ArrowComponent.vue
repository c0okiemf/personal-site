<script>
    const randSign = () => {
        return Math.random() > 0.5 ? 1 : -1;
    };

    export default {
        inject: ['provider'],
        data: function () {
            return {
                fps: 10,
                lineWidth: 15,
                lineCap: 'round',
                maxOffset: 7,
                shadowsAmount: 3,
                defaultOffset: {
                    leftX: 20,
                    leftY: 20,
                    rightX: 80,
                    rightY: 20,
                    midX: 50,
                    midY: 50
                },
                offsets: [],
                mainFill: 'rgba(0,0,0,1)',
                miscFill: 'rgba(86,255,233,1)'
            }
        },
        mounted() {
            let that = this;
            setInterval(() => {
                let newOffsets = [];
                for (let i = 0; i < that.shadowsAmount; i++) {
                    newOffsets.push({
                        leftX: randSign() * (Math.random() * that.maxOffset) + that.defaultOffset.leftX,
                        leftY: randSign() * (Math.random() * that.maxOffset) + that.defaultOffset.leftY,
                        rightX: randSign() * (Math.random() * that.maxOffset) + that.defaultOffset.rightX,
                        rightY: randSign() * (Math.random() * that.maxOffset) + that.defaultOffset.rightY,
                        midX: randSign() * (Math.random() * that.maxOffset) + that.defaultOffset.midX,
                        midY: randSign() * (Math.random() * that.maxOffset) + that.defaultOffset.midY
                    });
                }
                that.offsets = newOffsets;
            }, (1 / that.fps) * 1000);
        },
        render() {
            if(!this.provider.ctx) return;
            const ctx = this.provider.ctx;
            this.initCtx(ctx);
            this.offsets.forEach((offset) => {
                this.stroke(ctx, this.miscFill, {
                    fromX: this.wC(offset.leftX),
                    fromY: this.hC(offset.leftY),
                    toX: this.wC(offset.midX),
                    toY: this.hC(offset.midY)
                });
                this.stroke(ctx, this.miscFill, {
                    fromX: this.wC(offset.midX),
                    fromY: this.hC(offset.midY),
                    toX: this.wC(offset.rightX),
                    toY: this.hC(offset.rightY)
                });
            });
            this.stroke(ctx, this.mainFill, {
                fromX: this.wC(this.defaultOffset.leftX),
                fromY: this.hC(this.defaultOffset.leftY),
                toX: this.wC(this.defaultOffset.midX),
                toY: this.hC(this.defaultOffset.midY)
            });
            this.stroke(ctx, this.mainFill, {
                fromX: this.wC(this.defaultOffset.midX),
                fromY: this.hC(this.defaultOffset.midY),
                toX: this.wC(this.defaultOffset.rightX),
                toY: this.hC(this.defaultOffset.rightY)
            });
        },
        methods: {
            wC(percent) {
                return Math.floor((this.provider.ctx.canvas.width / 100) * percent);
            },
            hC(percent) {
                return Math.floor((this.provider.ctx.canvas.height / 100) * percent);
            },
            initCtx(ctx) {
                ctx.clearRect(0, 0, this.wC(100), this.hC(100));
                ctx.lineWidth = this.lineWidth;
                ctx.lineCap = this.lineCap;
            },
            stroke(ctx, strokeStyle, coords = {
                fromX: 0,
                fromY: 0,
                toX: 0,
                toY: 0
            }) {
                ctx.beginPath();
                ctx.moveTo(coords.fromX, coords.fromY);
                ctx.lineTo(coords.toX, coords.toY);
                ctx.strokeStyle = strokeStyle;
                ctx.stroke();
            }
        }
    }
</script>