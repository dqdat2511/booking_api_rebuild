PGDMP                         {         
   Db_Booking    15.4    15.4 *    7           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            8           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            9           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            :           1262    31092 
   Db_Booking    DATABASE     �   CREATE DATABASE "Db_Booking" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Vietnamese_Vietnam.1258';
    DROP DATABASE "Db_Booking";
                postgres    false            �            1259    31147 
   bus_floors    TABLE     Z   CREATE TABLE public.bus_floors (
    id uuid NOT NULL,
    name character varying(255)
);
    DROP TABLE public.bus_floors;
       public         heap    postgres    false            �            1259    31555    bus_slot    TABLE     �   CREATE TABLE public.bus_slot (
    id uuid NOT NULL,
    is_available boolean DEFAULT true NOT NULL,
    name_slot character varying(255),
    trip_id uuid,
    bus_floor_id uuid,
    bus_type_id uuid
);
    DROP TABLE public.bus_slot;
       public         heap    postgres    false            �            1259    31219 	   bus_types    TABLE     �   CREATE TABLE public.bus_types (
    id uuid NOT NULL,
    convenients text,
    maxslot integer NOT NULL,
    name character varying(255),
    numbers_floor integer NOT NULL
);
    DROP TABLE public.bus_types;
       public         heap    postgres    false            �            1259    31561    customer_tickes    TABLE     k   CREATE TABLE public.customer_tickes (
    customer_id uuid NOT NULL,
    customer_seat_id uuid NOT NULL
);
 #   DROP TABLE public.customer_tickes;
       public         heap    postgres    false            �            1259    31514    customer_tickets    TABLE     ?   CREATE TABLE public.customer_tickets (
    id uuid NOT NULL
);
 $   DROP TABLE public.customer_tickets;
       public         heap    postgres    false            �            1259    31564    tickets    TABLE       CREATE TABLE public.tickets (
    id uuid NOT NULL,
    address character varying(255),
    code_ticket character varying(255),
    customer_name character varying(255),
    customer_phone character varying(255),
    num_tickets integer NOT NULL,
    trip_id uuid
);
    DROP TABLE public.tickets;
       public         heap    postgres    false            �            1259    31417 	   time_trip    TABLE     �   CREATE TABLE public.time_trip (
    id integer NOT NULL,
    end_day date,
    end_time time(6) without time zone,
    start_day date,
    start_time time(6) without time zone
);
    DROP TABLE public.time_trip;
       public         heap    postgres    false            �            1259    31416    time_trip_id_seq    SEQUENCE     �   CREATE SEQUENCE public.time_trip_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.time_trip_id_seq;
       public          postgres    false    217            ;           0    0    time_trip_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.time_trip_id_seq OWNED BY public.time_trip.id;
          public          postgres    false    216            �            1259    31571    trips    TABLE     �   CREATE TABLE public.trips (
    id uuid NOT NULL,
    name character varying(255),
    timetrip_id integer,
    bus_type_id uuid
);
    DROP TABLE public.trips;
       public         heap    postgres    false            �           2604    31420    time_trip id    DEFAULT     l   ALTER TABLE ONLY public.time_trip ALTER COLUMN id SET DEFAULT nextval('public.time_trip_id_seq'::regclass);
 ;   ALTER TABLE public.time_trip ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217            3           2613    31236    31236    BLOB     &   SELECT pg_catalog.lo_create('31236');
 &   SELECT pg_catalog.lo_unlink('31236');
                postgres    false            *          0    31147 
   bus_floors 
   TABLE DATA           .   COPY public.bus_floors (id, name) FROM stdin;
    public          postgres    false    214   �/       /          0    31555    bus_slot 
   TABLE DATA           c   COPY public.bus_slot (id, is_available, name_slot, trip_id, bus_floor_id, bus_type_id) FROM stdin;
    public          postgres    false    219   t0       +          0    31219 	   bus_types 
   TABLE DATA           R   COPY public.bus_types (id, convenients, maxslot, name, numbers_floor) FROM stdin;
    public          postgres    false    215   �6       0          0    31561    customer_tickes 
   TABLE DATA           H   COPY public.customer_tickes (customer_id, customer_seat_id) FROM stdin;
    public          postgres    false    220   �7       .          0    31514    customer_tickets 
   TABLE DATA           .   COPY public.customer_tickets (id) FROM stdin;
    public          postgres    false    218   }9       1          0    31564    tickets 
   TABLE DATA           p   COPY public.tickets (id, address, code_ticket, customer_name, customer_phone, num_tickets, trip_id) FROM stdin;
    public          postgres    false    221   �9       -          0    31417 	   time_trip 
   TABLE DATA           Q   COPY public.time_trip (id, end_day, end_time, start_day, start_time) FROM stdin;
    public          postgres    false    217   �:       2          0    31571    trips 
   TABLE DATA           C   COPY public.trips (id, name, timetrip_id, bus_type_id) FROM stdin;
    public          postgres    false    222   3;       <           0    0    time_trip_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.time_trip_id_seq', 3, true);
          public          postgres    false    216            4          0    0    BLOBS    BLOBS                             false   �;       �           2606    31151    bus_floors bus_floors_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.bus_floors
    ADD CONSTRAINT bus_floors_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.bus_floors DROP CONSTRAINT bus_floors_pkey;
       public            postgres    false    214            �           2606    31560    bus_slot bus_slot_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.bus_slot
    ADD CONSTRAINT bus_slot_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.bus_slot DROP CONSTRAINT bus_slot_pkey;
       public            postgres    false    219            �           2606    31225    bus_types bus_types_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.bus_types
    ADD CONSTRAINT bus_types_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.bus_types DROP CONSTRAINT bus_types_pkey;
       public            postgres    false    215            �           2606    31518 &   customer_tickets customer_tickets_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.customer_tickets
    ADD CONSTRAINT customer_tickets_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.customer_tickets DROP CONSTRAINT customer_tickets_pkey;
       public            postgres    false    218            �           2606    31570    tickets tickets_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_pkey;
       public            postgres    false    221            �           2606    31422    time_trip time_trip_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.time_trip
    ADD CONSTRAINT time_trip_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.time_trip DROP CONSTRAINT time_trip_pkey;
       public            postgres    false    217            �           2606    31575    trips trips_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.trips
    ADD CONSTRAINT trips_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.trips DROP CONSTRAINT trips_pkey;
       public            postgres    false    222            �           2606    31579 %   bus_slot uk_7uq20tnrfwwfl5ac9xy6i458o 
   CONSTRAINT     g   ALTER TABLE ONLY public.bus_slot
    ADD CONSTRAINT uk_7uq20tnrfwwfl5ac9xy6i458o UNIQUE (bus_type_id);
 O   ALTER TABLE ONLY public.bus_slot DROP CONSTRAINT uk_7uq20tnrfwwfl5ac9xy6i458o;
       public            postgres    false    219            �           2606    31577 %   bus_slot uk_en1yt16yfnmgmpg2b2opoc5wf 
   CONSTRAINT     h   ALTER TABLE ONLY public.bus_slot
    ADD CONSTRAINT uk_en1yt16yfnmgmpg2b2opoc5wf UNIQUE (bus_floor_id);
 O   ALTER TABLE ONLY public.bus_slot DROP CONSTRAINT uk_en1yt16yfnmgmpg2b2opoc5wf;
       public            postgres    false    219            �           2606    31595 +   customer_tickes fk1whl6hbx93ya2rqgi4oh4tm6x    FK CONSTRAINT     �   ALTER TABLE ONLY public.customer_tickes
    ADD CONSTRAINT fk1whl6hbx93ya2rqgi4oh4tm6x FOREIGN KEY (customer_id) REFERENCES public.tickets(id);
 U   ALTER TABLE ONLY public.customer_tickes DROP CONSTRAINT fk1whl6hbx93ya2rqgi4oh4tm6x;
       public          postgres    false    220    221    3218            �           2606    31610 !   trips fk3swq3aydmgckp9tdidd8164o7    FK CONSTRAINT     �   ALTER TABLE ONLY public.trips
    ADD CONSTRAINT fk3swq3aydmgckp9tdidd8164o7 FOREIGN KEY (bus_type_id) REFERENCES public.bus_types(id);
 K   ALTER TABLE ONLY public.trips DROP CONSTRAINT fk3swq3aydmgckp9tdidd8164o7;
       public          postgres    false    215    222    3206            �           2606    31600 #   tickets fkbcjlnu2low7r5vfimxextqab9    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT fkbcjlnu2low7r5vfimxextqab9 FOREIGN KEY (trip_id) REFERENCES public.trips(id);
 M   ALTER TABLE ONLY public.tickets DROP CONSTRAINT fkbcjlnu2low7r5vfimxextqab9;
       public          postgres    false    222    3220    221            �           2606    31580 $   bus_slot fkfow5n7w9gm22gc7s0ean5yev7    FK CONSTRAINT     �   ALTER TABLE ONLY public.bus_slot
    ADD CONSTRAINT fkfow5n7w9gm22gc7s0ean5yev7 FOREIGN KEY (bus_floor_id) REFERENCES public.bus_floors(id);
 N   ALTER TABLE ONLY public.bus_slot DROP CONSTRAINT fkfow5n7w9gm22gc7s0ean5yev7;
       public          postgres    false    219    214    3204            �           2606    31585 $   bus_slot fkgvw52hylqmqg5cuul80ilvt7t    FK CONSTRAINT     �   ALTER TABLE ONLY public.bus_slot
    ADD CONSTRAINT fkgvw52hylqmqg5cuul80ilvt7t FOREIGN KEY (bus_type_id) REFERENCES public.bus_types(id);
 N   ALTER TABLE ONLY public.bus_slot DROP CONSTRAINT fkgvw52hylqmqg5cuul80ilvt7t;
       public          postgres    false    219    215    3206            �           2606    31590 +   customer_tickes fkhm2o1ob0g79gpvfou7iw4mxvh    FK CONSTRAINT     �   ALTER TABLE ONLY public.customer_tickes
    ADD CONSTRAINT fkhm2o1ob0g79gpvfou7iw4mxvh FOREIGN KEY (customer_seat_id) REFERENCES public.bus_slot(id);
 U   ALTER TABLE ONLY public.customer_tickes DROP CONSTRAINT fkhm2o1ob0g79gpvfou7iw4mxvh;
       public          postgres    false    219    3212    220            �           2606    31605 !   trips fks0qykyuj59nhfhy2773jl3isx    FK CONSTRAINT     �   ALTER TABLE ONLY public.trips
    ADD CONSTRAINT fks0qykyuj59nhfhy2773jl3isx FOREIGN KEY (timetrip_id) REFERENCES public.time_trip(id);
 K   ALTER TABLE ONLY public.trips DROP CONSTRAINT fks0qykyuj59nhfhy2773jl3isx;
       public          postgres    false    3208    222    217            *   m   x�-�1�0 �9��l�_��S�Hi��v�|w�I���l0J�B7b4�SdhI��N����<*1�ϥ�a�H$U���D��u��Z!��@�6QB�WC'f>W��m����i#�      /   2  x��X�m��\�Ӌ��\��Tp7�DV����g� �a��@?9/9[�9��YEb�⾤�)��=������?�Rg*�������,�,�u������wϵt�)�[-Ri��S�6]yvR������zR�R$��fD���k?��_@�,�;��)�'�ZSJ�'']Py��.�7||��9J�y�窞���'оv[k��{�,ܙ���W,����:_@���ԤP�^�G���H穭�]O���k�]���Lg+Z��:=���nqA�i�mi�:e�ĢrZq�S�i�����nڞ8�GO��2�����������~�������r�க�,�V�Z�)Gc|�jO��S&>�	�_�Ts�p����T�˚�Vv,ݶ �>Jj�0�1��P�Ⱥeb���*�� �z�p��?�zb�NU�#�zh`��mqY�.����]a��L��Q�)��
7����>�U[��O�P��\Ι�x���'c���2��sz��c �j��R|w%z��8�Ƈo����h�x&m��C}�k��`��Ν7Y�(U/tt�.�~>�'f���m� �1���몐B�3�%-(6���I���;g�ױ0
��恏�G�Jo�vN�k��!~1�cwL����\]/j{Z�,4D���/�vn���w�Pn�k�V�\^����Ŭ�1Xy}�O>�KlU���z�.@XXF=���� �4 �Wfϒ�&�]�c�ű������� O=�<�h`��A��V�NKTe������^	�� �Z�H!9���A;y�8�a����:�:�	��8��'�@�6������"�]�(�˰c�'�@G뷚E���Fn
�
��f2m�Of?�ۄ�z�Q���������}��56��5�yX
���A(w�U�1�Ǔ~��� �	��?��/�>����di���^P��X"V;&�@e�@HG�0�<��_<�*j�5�?�_��KX@�DF��%�_�L �X0粹_g����އ�t���/^�
��0)��Z��ְ2�
SiV��_�h��E`\��wop��g���T���2P�Q��G�Y��p?�n*����^o����Qs�\O��C"�_<��v`��UE���ZĵD�s������/��o�3f3C��-	��A��A}�V�1:B�QZ��,h�j�ݕ������礹��@'�ѡ�ʡ��ln�Г��}�R�e�}�;"c�("�����h�]�UFEnc�H.dV�Y�o���?�'m�%I-q�7��2��`���a� ��mX/�4+�tv� �o83�C��g�C}�V��lM�3�!p X����L�i�o���g��:�+Z&��4�������'mX�1b�ϝk�s'vܖu���I["f��zK��x2�*Om:C#�P���SVT,}��C�*�:RC7��������e��p�P�U�4]��N�x;앿���sT<�/���<�LD�CWK�*_q}�ל�2��
j��y_�a���U�78�d�Ү�$Z�F-B>N4e��_�'05ou�1���gхE񦑼������������6:��      +     x�-��N�0Fg�)��Ql�晅�n�,�s�E�@�@;#Ā*&$"Ĉ��V1��=�&$���ǂ�Y��)�Xb�r���3A�,JR��n����G�P��Oo_L������{��MQ'�B���L��F$��(Y!��q%\bAI�I�˜'aL$C��(�^���{��,2�>k觿�:aj���>��M	�ݜ�&'8O��p�+������r ��u�5�� U;WOԍR!FљS��������)�t��-
�� � ��o�      0   �  x���˱�0D�v.�B���2} ����7v��>�&�m#� ��`�QXuc�UA�笳.T�ppG�����"�|ǵyw:��[4v��4�y�~���Hx@�I���Պ�f�zmR���[�3E1�͡k^o�|ƺ��|Ɇ5爓�3��HI��@:į�bw���{�~����@P�6{�Ei�9���#�e9N�[X ���c�ě-'�o$��U���n���;�Xv�m-�%��L��N���[f��7�0��@���a���{0~#�=�=M�n���,��I\�{Y%�H��hѥ��v�q[���e.4������9��S�S-�{�i���o�{e���U\��)a�]���Y&w�cwOe�h�y[w���v��g�ϊ3�@�l;�������ܠ�������,��      .      x������ � �      1   B  x���1�Q�zf��Hr����6���ae3���o��Z\�;�7�+��;q�A����J����0� ����j��:�G]����|�yyzs��<{���P�.�"<=��������ZnO_�&�H�nMtd���QP}�J��֔�<Z7��[���@����ji�u������Ga�d�m��>�'�Z{��U�2�CQ�8ҢqY�mG���C6�0gԞ)좠ri�nߝ|ݗ??��Et��OH���5?�4ȹ�ѣ��(5��J1SN�J	µ@p&`g�VL]�*å<����ȷ�q��O�y���      -   7   x�3�4202�50�52�44�20 "d1s���J<*�QT�a�������� �>�      2   �   x�]��1�u;E��/�  1�}`N�6` �X�M8��~P��^	2��A�2�6_��\S2��vt��1������}:����:O��KPi9S �д�jV���$6`,( �a�|�>!`��{�5b��|����N͐餍�"��b���0��H�l�+k�)�=�      4   z   -   x�" ��Máy lạnh, giường, quá tốt)�4          