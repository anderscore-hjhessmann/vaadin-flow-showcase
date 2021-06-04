package com.anderscore.samples.views.map;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.anderscore.samples.components.leafletmap.LeafletMap;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.anderscore.samples.views.main.MainView;

@Route(value = "map", layout = MainView.class)
@PageTitle("Map")
public class MapView extends VerticalLayout {

    private LeafletMap map = new LeafletMap();

    public MapView() {
        setSizeFull();
        setPadding(false);
        map.setSizeFull();
        map.setView(55.0, 10.0, 4);
        add(map);
    }
}
