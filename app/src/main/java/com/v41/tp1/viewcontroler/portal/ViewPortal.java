package com.v41.tp1.viewcontroler.portal;

import com.v41.tp1.modele.portal.ModelPortal;

/**
 * This interface allows both the model and the controller
 * to notify the view of any changes to be applied to the display.
 */
public interface ViewPortal
{
    void notify(ModelPortal modelPortal);
}