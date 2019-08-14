package cn.stormbirds.allpay.model.web;


import cn.stormbirds.allpay.common.utils.clusterutils.Bounds;

/**
 * <p>
 * PACKAGE_NAME
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/14 17:40
 */


public class FindRoundEqModelParent {
    private int size;
    private Bounds bounds;

    public FindRoundEqModelParent(int size, Bounds bounds) {
        this.size = size;
        this.bounds = bounds;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }
}
